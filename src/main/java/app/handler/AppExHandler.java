package app.handler;

import app.exception.post.PostNotFoundEx;
import app.exception.user.UserNotFoundEx;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@ControllerAdvice
public class AppExHandler {

    @ExceptionHandler(UserNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RedirectView handleUserNotFound(){
        log.warn("User not found");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(PostNotFoundEx.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public RedirectView handlePostNotFound(){
        log.warn("Post not found");
        return new RedirectView("/dashboard");
    }
}
