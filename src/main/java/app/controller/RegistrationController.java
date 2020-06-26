package app.controller;

import app.exception.NotEqualException;
import app.exception.NotUniqueException;
import app.form.UserForm;
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
  @ExceptionHandler({NotUniqueException.class, NotEqualException.class})
  public RedirectView handle_post(UserForm userForm, Model model) {
    String email = userForm.getEmail();
    String username = userForm.getUsername();
    String password = userForm.getPassword();
    String passConf = userForm.getPassConf();

    if (!userService.checkDuplicate(email, username)) {
      throw new NotUniqueException();
    }
    else if(!password.equals(passConf)) {
      throw new NotEqualException();
    }
    return new RedirectView("anket");
  }
}
