package app.controller;

import app.form.FormChat;
import app.security.UserrDetails;
import app.service.BlockedService;
import app.service.MessageService;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/message")
public class MessageController {

  private final MessageService messageService;
  private final UserService userService;
  private final BlockedService blockedService;

  /**
   * http://localhost:8085/message
   */
  @GetMapping()
  public String handle_get(Model model, Authentication au) {

    model.addAttribute("loggedUserId", getLoggedUser(au).getId());
    model.addAttribute("loggedUser", userService.findByEmail(getLoggedUser(au).getUsername()));
    model.addAttribute("connections",
            messageService.findLastMessagesbyUser(getLoggedUser(au).getId()));

    return "chat-main";
  }

  /**
   * http://localhost:8085/message/2
   */
  @GetMapping("/{id}")
  public String handle_get(@PathVariable String id, Model model, Authentication au) {
    long loggedUserId = getLoggedUser(au).getId();

    blockedService.checkBlock(id, loggedUserId);

    model.addAttribute("loggedUserId", loggedUserId);
    model.addAttribute("loggedUser", userService.findByEmail(getLoggedUser(au).getUsername()));
    model.addAttribute("currentUser", userService.findById(id));
    model.addAttribute("messages", messageService.findMessagesBetween(loggedUserId, id));
    return "chat-private";

  }

  @PostMapping("/{id}")
  public RedirectView handle_post(FormChat form, @PathVariable String id, Authentication au) {
    messageService.sendMessage(String.valueOf(getLoggedUser(au).getId()), id, form.getMessage());
    return new RedirectView("{id}");
  }

  UserrDetails getLoggedUser(Authentication authentication) {
    return (UserrDetails) authentication.getPrincipal();
  }
}
