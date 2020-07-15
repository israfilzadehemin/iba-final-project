package app.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Log4j2
@Controller
@RequestMapping("/signin")
public class LoginController {

  // http://localhost:8085/signin

  @GetMapping
  public String handle_get() {
    return "signin";
  }

}
