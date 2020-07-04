package app.controller;

import app.form.FormSearch;
import app.service.CategoryService;
import app.service.WishedService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/wishlist")
public class WishedController {

  private final WishedService wishedService;
  private final CategoryService categoryService;

  public WishedController(WishedService wishedService, CategoryService categoryService) {
    this.wishedService = wishedService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public String handle_get(Model model) {
    model.addAttribute("posts", wishedService.findWishedPosts("1"));
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("wished", true);
    return "dashboard";
  }

  @RequestMapping("/add/{id}")
  public String handle_add(@PathVariable String id) {
    wishedService.addWished("1", id);
    return "redirect:/wishlist";
  }

  @RequestMapping("/delete/{id}")
  public String handle_delete(@PathVariable String id) {
    wishedService.deleteWished("1", id);
    return "redirect:/wishlist";
  }

  @PostMapping()
  public RedirectView handle_post(Model model, FormSearch form) {
    model.addAttribute("name", form.getKeyword());
    model.addAttribute("cat", form.getCategory());
    return new RedirectView("search");
  }
}
