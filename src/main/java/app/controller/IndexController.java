package app.controller;

import app.form.FormAd;
import app.service.AdService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

  // http://localhost:8080/

  private final AdService adService;

  @GetMapping
  public String handle_get() {
    return "index";
  }

  @PostMapping
  public RedirectView handle_post(FormAd form) {
    String fullName = form.getFullName();
    String number = form.getNumber();
    String time = form.getTime();
    adService.sendAdRequest(fullName, number, time);
    return new RedirectView("/");
  }
}
