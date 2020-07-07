package app.controller;

import app.service.MailSenderService;
import app.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("forgotpassword")
public class MailSenderController {
    private final  MailSenderService sender;
    private final UserService service;

    public MailSenderController(MailSenderService sender, UserService service) {
        this.sender = sender;
        this.service = service;
    }

    /**
     * http://localhost:8085/forgotpassword/send?mail=rza.ayshan@gmail.com
     */

    @RequestMapping("send")
    public void sendMail(@RequestParam String mail) throws MessagingException {
        String body =
                "Can't remember your password for: ibatech ? Don't worry, we can help.\n" +
                "To reset your password just click the link below and follow the instructions:\n" +
                "http://localhost:8085/forgotpassword/changepassword";
        if(service.isUserExistByEmail(mail))
            sender.send(mail, "Forgot your password?", body);
    }

}
