package app.controller;

import app.exception.input.NoParamEx;
import app.form.FormSearch;
import app.service.CategoryService;
import app.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/search")
public class SearchController {

  private final PostService postService;
  private final CategoryService categoryService;

  public SearchController(PostService postService, CategoryService categoryService) {
    this.postService = postService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String handle_get(HttpServletRequest req, Model model ) {
    String name = req.getParameter("name");
    String category = req.getParameter("cat");
    if (name == null || category ==null) throw new NoParamEx();
    model.addAttribute("posts", postService.findFiltered(name, category));
    model.addAttribute("categories", categoryService.findAll());

    return "dashboard";
  }

  @PostMapping()
  public RedirectView handle_post(Model model, FormSearch form) {
    model.addAttribute("name", form.getKeyword());
    model.addAttribute("cat", form.getCategory());
    return new RedirectView("search");
  }
}
