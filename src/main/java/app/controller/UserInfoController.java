package app.controller;

import app.form.FormInfo;
import app.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;

@Log4j2
@Controller
@RequestMapping("/info")
public class UserInfoController {

  // http://localhost:8085/info

  private final UserService userService;

  public UserInfoController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public String handle_get() {
    return "anket";
  }

  @SneakyThrows
  @PostMapping
  public RedirectView handle_post(FormInfo form, @RequestParam("image") MultipartFile file) {
    String username = form.getUsername();
    String name = form.getName();
    String surname = form.getSurname();
    String city = form.getCity();
    String number = form.getNumber();

    if (!userService.fillInfo("1", username, name, surname, city, number, file)) {
      log.warn("Something went wrong filling UserInfo");
    }
    return new RedirectView("dashboard");
  }
}
