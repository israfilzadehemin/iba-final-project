package app.controller;

import app.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
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

  // http://localhost:8080/dashboard

  @GetMapping
  public String handle_get() {
    postService.findAll();
    return "dashboard";
  }

  // http://localhost:8080/dashboard/byfilter?name=sofa&category=home

  @PostMapping("/byfilter")
  public RedirectView handle_post(@RequestParam("name") String keyword,
                                @RequestParam("category") String category, HttpServletRequest rq) {

    String button = rq.getParameter("button");
    if (button == null) return new RedirectView("dashboard");

    postService.findFiltered(keyword, category);
    return new RedirectView("dashboard");
  }
}
