package app.controller;

import app.form.FormNewPass;
import app.form.FormReset;
import app.service.MailSenderService;
import app.service.ResetPasswordService;
import app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.Random;

@Controller
@RequestMapping("resetpass")
public class ResetPasswordController {
  private final MailSenderService sender;
  private final UserService userService;
  private final ResetPasswordService resetPasswordService;

  public ResetPasswordController(MailSenderService sender, UserService userService, ResetPasswordService resetPasswordService) {
    this.sender = sender;
    this.userService = userService;
    this.resetPasswordService = resetPasswordService;
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
    String token = String.valueOf(r.nextInt(999999999));

    String link = "http://localhost:8080/resetpass/newpass?email=" + form.getEmail() + "&token=" + token;
    String text = String.format("%s \n %s", message, link);
    if (userService.isUserExistByEmail(form.getEmail())) {
      resetPasswordService.addOrUpdateResetToken(form.getEmail(), token);
      sender.send(form.getEmail(), "Reset your password for HANDY profile", text);
      model.addAttribute("sent", true);
    } else {
      model.addAttribute("sent", false);
    }
    return new RedirectView("/resetpass");
  }

  @GetMapping("/newpass")
  public String handle_newpass_get(@RequestParam() String email, @RequestParam String token) {
    resetPasswordService.validateToken(email, token);
    return "new-pass";
  }

  @PostMapping("/newpass")
  public String handle_newpass_post(@RequestParam() String email,
                                    @RequestParam() String token,
                                    FormNewPass form) {
    resetPasswordService.validateToken(email, token);
    userService.updatePassword(email, form.getPass(), form.getConPass());
    return "redirect:/signin";
  }

}
