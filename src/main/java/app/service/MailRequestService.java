package app.service;

import app.entity.Category;
import app.entity.Post;
import app.exception.input.PostEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.exception.post.NoPostEx;
import app.exception.post.NotAuthorizedEx;
import app.exception.post.PostNotFoundEx;
import app.repo.CategoryRepo;
import app.repo.PostRepo;
import app.tool.ConverterTool;
import app.tool.FileTool;
import app.tool.ValidationTool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class MailRequestService {
  private final MailSenderService mailSenderService;

  public boolean sendAdRequest(String fullName, String number, String time) {
    try {
      String receiver = "israfilzadehemin@gmail.com";
      String subject = "Alert! New Ad Request has been received";
      String body = String.format("Name and Surname: %s\n Mobile phone: %s\n Available time: %s", fullName, number, time);
      mailSenderService.send(receiver, subject, body);
      log.info("AD request has been sent successfully");
      return true;
    } catch (MessagingException e) {
      log.warn("MessagingException happened: from MailRequestService.sendAdRequest()");
      return false;
    }
  }

  public boolean sendFeedback(String email, String text) {
    try {
      String receiver = "israfilzadehemin@gmail.com";
      String subject = "Alert! New Feedback has been received";
      String body = String.format("Sender: %s\n Text: %s", email, text);
      mailSenderService.send(receiver, subject, body);
      log.info("Feedback has been sent successfully");
      return true;
    } catch (MessagingException e) {
      log.warn("MessagingException happened: from MailRequestService.sendFeedback()");
      return false;
    }
  }
}
