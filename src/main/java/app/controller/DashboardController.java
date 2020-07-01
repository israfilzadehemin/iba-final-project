package app.controller;

import app.form.FormSearch;
import app.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

  private final PostService postService;

  public DashboardController(PostService postService) {
    this.postService = postService;
  }

  // http://localhost:8085/dashboard

  @GetMapping
  public String handle_get(Model model) {

    model.addAttribute("posts", postService.findAll());
    return "dashboard";
  }

   //http://localhost:8085/search?name=Em&cat=1

  @PostMapping()
  public RedirectView handle_post(Model model, FormSearch form, HttpServletRequest rq) {

    model.addAttribute("name", form.getKeyword());
    model.addAttribute("cat", form.getCategory());
    return new RedirectView("search");
  }

}
