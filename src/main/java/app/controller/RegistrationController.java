package app.controller;

import app.form.FormReg;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/signup")
public class RegistrationController {

  // http://localhost:8085/signup

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_get() {
    return "signup";
  }

  @PostMapping
  public RedirectView handle_post(FormReg form) {
    String email = form.getEmail();
    String password = form.getPass();
    String conPass = form.getConPass();

    if (!userService.register(email, password, conPass)) {
      log.warn("Registration canceled!");
    }
    return new RedirectView("info");
  }
}
