package app.controller;

import app.form.FormSearch;
import app.security.UserrDetails;
import app.service.CategoryService;
import app.service.PostService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

  private final UserService userService;
  private final PostService postService;
  private final CategoryService categoryService;


  // http://localhost:8085/dashboard

  @GetMapping
  public String handle_get(Model model, Authentication au) {
    model.addAttribute("loggedUser", userService.findByEmail(getLoggedUser(au).getUsername()));
    model.addAttribute("posts", postService.findAll());
    model.addAttribute("categories", categoryService.findAll());
    return "dashboard";
  }

   //http://localhost:8085/search?name=Em&cat=1

  @PostMapping()
  public RedirectView handle_post(Model model, FormSearch form) {
    model.addAttribute("name", form.getKeyword());
    model.addAttribute("cat", form.getCategory());
    return new RedirectView("search");
  }


  UserrDetails getLoggedUser(Authentication authentication) {
    return (UserrDetails) authentication.getPrincipal();
  }
}
