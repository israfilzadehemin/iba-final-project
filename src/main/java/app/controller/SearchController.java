package app.controller;

import app.exception.NoParamEx;
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

@Log4j2
@Controller
@RequestMapping("/search")
public class SearchController {

  private final PostService postService;

  public SearchController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String handle_get(HttpServletRequest req, Model model ) {
    String name = req.getParameter("name");
    String category = req.getParameter("cat");
    if (name == null || category ==null) throw new NoParamEx();
    model.addAttribute("posts", postService.findFiltered(name, category));
    return "dashboard";
  }

  @PostMapping()
  public RedirectView handle_post(Model model, FormSearch form) {
    model.addAttribute("name", form.getKeyword());
    model.addAttribute("cat", form.getCategory());
    return new RedirectView("search");
  }
}
