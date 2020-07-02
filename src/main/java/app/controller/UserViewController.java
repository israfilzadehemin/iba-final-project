package app.controller;

import app.entity.Userr;
import app.form.FormUser;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserViewController {

  private final UserService userService;

  public UserViewController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public RedirectView handle_get(Model model, FormUser form) {
//    Userr user = userService.findById("1");
//    model.addAttribute("user", user);

    String name = form.getName();
    String surname = form.getSurname();
    String city = form.getCity();
    String number = form.getNumber();
    return new RedirectView("user");
  }
}
