package app.controller;

import app.entity.User;
import app.exception.NotEqualEx;
import app.exception.NotUniqueEx;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/signup")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_get(Model model) {
    model.addAttribute("error", "noerror");
    return "signup";
  }

  @PostMapping
  @ExceptionHandler({NotUniqueEx.class, NotEqualEx.class})
  public RedirectView handle_post(User user, Model model) {
    String mail = user.getMail();
    String username = user.getUsername();
    String password = user.getPassword();
    String passConf = user.getPassConf();

    if (!userService.checkDuplicate(mail, username)) {
      throw new NotUniqueEx();
    }
    else if(!password.equals(passConf)) {
      throw new NotEqualEx();
    }
    return new RedirectView("anket");
  }
}
