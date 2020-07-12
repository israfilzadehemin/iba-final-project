package app.controller;

import app.entity.Post;
import app.form.FormUser;
import app.security.UserrDetails;
import app.service.PostService;
import app.service.UserService;
import app.tool.PageableTool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/myposts")
public class PostViewController {

  // http://localhost:8085/myposts

  private final PostService postService;
  private final UserService userService;
  private final PageableTool<Post> pageableTool;

  /**
   * http://localhost:8080/myposts/2?sortField=name&sortDir=asc
   */


  @GetMapping("/{currentPage}")
  public String handle_get(Model model, Authentication au,
                           @PathVariable("currentPage") Optional<Integer> currentPageOp,
                           @RequestParam("sortField") Optional<String> sortFieldOp,
                           @RequestParam("sortDir") Optional<String> sortDirOp) {

    int currentPage = currentPageOp.orElse(1);
    String sortField = sortFieldOp.orElse("name");
    String sortDir = sortDirOp.orElse("asc");
    Page<Post> page = postService.findByUser(String.valueOf(getLoggedUser(au).getId()),
            currentPage, sortField, sortDir);

    pageableTool.controller(page, model, currentPage, sortField, sortDir);
    model.addAttribute("loggedUser", userService.findByEmail(getLoggedUser(au).getUsername()));


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
