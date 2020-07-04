package app.controller;

import app.service.BlockedService;
import app.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
@RequestMapping("/user")
public class UserViewController {

  private final UserService userService;
  private final BlockedService blockedService;

  public UserViewController(UserService userService, BlockedService blockedService) {
    this.userService = userService;
    this.blockedService = blockedService;
  }

  @GetMapping("/{id}")
  public String handle_get(@PathVariable String id, Model model) {
    model.addAttribute("user", userService.viewUser(id, "1"));
    return "user";
  }

  @GetMapping("/block/{id}")
  public RedirectView handle_block(@PathVariable String id) {
    blockedService.addBlocked("1", id);
    return new RedirectView("/dashboard");
  }


}
