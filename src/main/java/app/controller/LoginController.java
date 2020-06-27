package app.controller;

import app.form.FormLogin;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/signin")
public class LoginController {

  // http://localhost:8080/signin

  private final UserService userService;

  public LoginController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_get(Model model) {
    model.addAttribute("error", "noerror");
    return "signin";
  }

  @PostMapping
  public RedirectView handle_post(FormLogin form, HttpServletRequest rq) {
    String login = form.getLogin();
    String pass = form.getPass();

    String button = rq.getParameter("button");
    if (button == null) return new RedirectView("signin");

    if (!userService.login(login, pass)) {
      log.warn("Login canceled!");
    }
    return new RedirectView("dashboard");
  }
}
