package app.exceptionhandler;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@Controller
public class GlobalExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public RedirectView handle_error(){
        log.warn("GlobalExceptionHandler: Error handled");
        return new RedirectView("/dashboard");
    }
}
