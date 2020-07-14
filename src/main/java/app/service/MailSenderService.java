package app.service;


import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@AllArgsConstructor
public class MailSenderService {

  private final JavaMailSender javaMailSender;

  public void send(String to, String subject, String body) throws MessagingException {

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);

    helper.setSubject(subject);
    helper.setTo(to);
    helper.setText(body, true);

    javaMailSender.send(message);

  }

}
