package app.controller;

import app.form.FormAd;
import app.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {

  // http://localhost:8080/

  private final MailRequestService mailRequestService;
  private final AboutUsService aboutUsService;
  private final MissionService missionService;
  private final PhoneService phoneService;
  private final AddressService addressService;

  @GetMapping
  public String handle_get(Model model) {
    model.addAttribute("aboutUs", aboutUsService.findAll());
    model.addAttribute("missions", missionService.findAll());
    model.addAttribute("phones", phoneService.findAll());
    model.addAttribute("addresses", addressService.findAll());
    return "index";
  }

  @PostMapping
  public RedirectView handle_post(FormAd form, Model model) {
    String fullName = form.getFullName();
    String number = form.getNumber();
    String time = form.getTime();
    mailRequestService.sendAdRequest(fullName, number, time);
    model.addAttribute("process", "adv");
    return new RedirectView("/");
  }
}
