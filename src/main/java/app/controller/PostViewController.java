package app.controller;

import app.entity.Post;
import app.form.FormUser;
import app.security.UserrDetails;
import app.service.PostService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/myposts")
public class PostViewController {

  // http://localhost:8085/myposts

  private final PostService postService;
  private final UserService userService;

  @GetMapping
  public String handle_get(Model model, Authentication au) {
    List<Post> posts = postService.findByUser(String.valueOf(getLoggedUser(au).getId()));
    model.addAttribute("loggedUser", userService.findByEmail(getLoggedUser(au).getUsername()));
    model.addAttribute("posts", posts);
    return "manage-post";
  }


  @GetMapping("/delete/{id}")
  public RedirectView handle_get(@PathVariable String id) {
    postService.deactivate(id);
      return new RedirectView("/myposts");
  }

  @PostMapping
  public String handle_post() {
    return "manage-post";
  }

  UserrDetails getLoggedUser(Authentication authentication) {
    return (UserrDetails) authentication.getPrincipal();
  }

}
