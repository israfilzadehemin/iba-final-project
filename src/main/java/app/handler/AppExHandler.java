package app.handler;

import app.exception.PostNotFoundEx;
import app.exception.UserNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class AppExHandler {

    @ExceptionHandler(UserNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RedirectView handleUserNotFound(){
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(PostNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RedirectView handlePostNotFound(){
        return new RedirectView("/dashboard");
    }
}
