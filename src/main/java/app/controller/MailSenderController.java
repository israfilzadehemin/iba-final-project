package app.controller;

import app.form.FormReset;
import app.service.MailSenderService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.Random;

@Controller
@RequestMapping("resetpass")
public class MailSenderController {
  private final MailSenderService sender;
  private final UserService service;

  public MailSenderController(MailSenderService sender, UserService service) {
    this.sender = sender;
    this.service = service;
  }

  /**
   * http://localhost:8080/resetpass/
   */
  @GetMapping
  public String handle_get() {
    return "forgot-pass";
  }

  /**
   * http://localhost:8080/resetpass/
   */
  @PostMapping
  public RedirectView handle_post(FormReset form, Model model) throws MessagingException {
    String message = "Someone sent reset password request for your HANDY profile. If it was you, please click link below to reset your password";
    Random r = new Random();

    String link = "http://localhost:8080/resetpass/newpass/email=" + form.getEmail() + "&token=" + r.nextInt(999999999);
    String text = String.format("%s \n %s", message, link);
    if (service.isUserExistByEmail(form.getEmail())) {
      sender.send(form.getEmail(), "Reset your password for HANDY profile", text);
      model.addAttribute("sent", true);
    } else {
      model.addAttribute("sent", false);
    }
    return new RedirectView("/resetpass");
  }

  @GetMapping("/newpass")
  RedirectView handle_newpass()

}
