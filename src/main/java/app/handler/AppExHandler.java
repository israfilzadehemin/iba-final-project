package app.handler;

import app.exception.post.PostNotFoundEx;
import app.exception.user.EmailNotUniqueEx;
import app.exception.user.EmptyInputEx;
import app.exception.user.InvalidPhoneNumberEx;
import app.exception.user.PassNotMatchEx;
import app.exception.user.UserNotFoundEx;
import app.exception.user.UsernameNotUniqueEx;
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

    @ExceptionHandler(EmailNotUniqueEx.class)
    public RedirectView handleEmailNotUnique(){
        log.warn("Email is not unique");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(UsernameNotUniqueEx.class)
    public RedirectView handleUsername(){
        log.warn("Username is not unique");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(PassNotMatchEx.class)
    public RedirectView handlePassNotMatch(){
        log.warn("Password is not matched");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(EmptyInputEx.class)
    public RedirectView handleEmptyInput(){
        log.warn("Input is empty");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(InvalidPhoneNumberEx.class)
    public RedirectView handleInvalidPhoneNumber(){
        log.warn("Phone number is invalid");
        return new RedirectView("/signup");
    }

}
