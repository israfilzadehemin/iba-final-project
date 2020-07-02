package app.controller;

import app.entity.Post;
import app.externalapi.cityapi.CityService;
import app.form.FormPost;
import app.service.CategoryService;
import app.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/mypost")
public class PostOperationsController {

  private final PostService postService;
  private final CategoryService categoryService;
  private final CityService cityService;

  public PostOperationsController(PostService postService, CategoryService categoryService, CityService cityService) {
    this.postService = postService;
    this.categoryService = categoryService;
    this.cityService = cityService;
  }

  // http://localhost:8085/mypost/1

  @GetMapping("/{id}")
  public String handle_get(Model model, @PathVariable String id) {
    if (!postService.isAuthorized("1", id)) log.warn("Update cancelled!");
    Post post = postService.findById(id);
    model.addAttribute("post", post);
    model.addAttribute("categories", categoryService.findAll());
    model.addAttribute("cities", cityService.getCities());
    return "a_u-post";
  }

  @PostMapping("/{id}")
  public RedirectView handle_post(FormPost form, @RequestParam("image") MultipartFile file) {
    String name = form.getName();
    String category = form.getCategory();  // should be added
    String city = form.getCity();
    String expiryDate = form.getExpiryDate();
    postService.addOrUpdate("1", name, city, expiryDate, file);
    return new RedirectView("dashboard");
  }
}
