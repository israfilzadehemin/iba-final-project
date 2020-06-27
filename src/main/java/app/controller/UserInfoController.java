package app.controller;

import app.form.FormInfo;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/info")
public class UserInfoController {

  // http://localhost:8080/info

  private final UserService userService;

  public UserInfoController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_get() {
    return "anket";
  }

  @PostMapping
  public RedirectView handle_post(FormInfo form) {
    String username = form.getUsername();
    String name = form.getName();
    String surname = form.getSurname();
    String city = form.getCity();
    String image = form.getImage();
    String number = form.getNumber();
    if (!userService.fillInfo(username, name, surname, city, image, number)) {
      log.warn("Something went wrong filling UserInfo");
    }
    return new RedirectView("dashboard");
  }
}
