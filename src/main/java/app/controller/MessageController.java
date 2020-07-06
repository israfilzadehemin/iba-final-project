package app.controller;

import app.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/message")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * http://localhost:8085/message
   */
  @GetMapping
  public String handle_get(Model model) {
    messageService.getMessagesBetween("21", "11");
    return "chat-main";
  }
}
