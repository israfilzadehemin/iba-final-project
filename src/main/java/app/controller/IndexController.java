package app.controller;

import app.form.FormAdv;
import app.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/index")
public class IndexController {

  // http://localhost:8085/index

  private final PostService postService;

  public IndexController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String handle_get() {
    return "index";
  }

  @PostMapping
  public RedirectView handle_post(FormAdv form, HttpServletRequest rq) {
    String button = rq.getParameter("button");
    if (button == null) return new RedirectView("index");

    String fullName = form.getFullName();
    String number = form.getNumber();
    String time = form.getTime();

    postService.fillAdver(fullName, number, time);
    return new RedirectView("dashboard");
  }
}
