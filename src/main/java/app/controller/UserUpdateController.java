package app.controller;

import app.form.FormUser;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserUpdateController {

  private final UserService userService;

  public UserUpdateController(UserService userService) {
    this.userService = userService;
  }

  // http://localhost:8080/user/update/1

  @GetMapping("/update/{id}")
  public String handle_get(@PathVariable String id, FormUser form, MultipartFile file) {
    String name = form.getName();
    String surname = form.getSurname();
    String city = form.getCity();
    String number = form.getNumber();

    userService.updateUser(id, name, surname, city, number, file);
    return "update-profile";
  }

  @PostMapping("/update{id}")
  public RedirectView handle_post(FormUser form, HttpServletRequest rq,
                                  @RequestParam("file") MultipartFile file, @PathVariable String id) {
    String button = rq.getParameter("button");
    if (button == null) return new RedirectView("/update");

    String name = form.getName();
    String surname = form.getSurname();
    String city = form.getCity();
    String number = form.getNumber();
    if (!userService.updateUser(id, name, surname, city, number, file)) {
      log.warn("User update canceled!");
    }
    return new RedirectView("dashboard");
  }
}
