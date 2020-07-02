package app.controller;

import app.entity.Post;
import app.form.FormUser;
import app.service.PostService;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/myposts")
public class PostViewController {

  // http://localhost:8085/myposts

  private final PostService postService;

  public PostViewController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String handle_get(Model model) {
    List<Post> posts = postService.findByUser("1");
    model.addAttribute("posts", posts);
    return "manage-post";
  }

//  @GetMapping("/{id}")
//  public RedirectView handle_get(@PathVariable String id) {
//      return new RedirectView("mypost");
//  }

  @PostMapping
  public String handle_post() {
    return "manage-post";
  }
}
