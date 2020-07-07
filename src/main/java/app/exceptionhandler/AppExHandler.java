package app.exceptionhandler;

import app.exception.input.NoParamEx;
import app.exception.input.*;
import app.exception.message.MessageNotFoundEx;
import app.exception.post.*;
import app.exception.user.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

@Log4j2
@ControllerAdvice
public class AppExHandler {

    @ExceptionHandler(UserNotFoundEx.class)
    public RedirectView handleUserNotFound(Model model){
        model.addAttribute("error", "usernotfound");
        log.warn("AppExHandler: UserNotFoundEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(PostNotFoundEx.class)
    public RedirectView handlePostNotFound(Model model){
        model.addAttribute("error", "postnotfound");
        log.warn("AppExHandler: PostNotFoundEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(EmailNotUniqueEx.class)
    public RedirectView handleEmailNotUnique(Model model){
        model.addAttribute("error", "emailnotunique");
        log.warn("AppExHandler: EmailNotUniqueEx");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(UsernameNotUniqueEx.class)
    public RedirectView handleUsername(Model model){
        model.addAttribute("error", "usernamenotunique");
        log.warn("AppExHandler: UsernameNotUniqueEx");
        return new RedirectView("/info");

    }

    @ExceptionHandler(PassNotMatchEx.class)
    public RedirectView handlePassNotMatch(Model model){
        model.addAttribute("error", "passnotmatch");
        log.warn("AppExHandler: PassNotMatchEx");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(EmptyInputEx.class)
    public RedirectView handleEmptyInput(Model model){
        model.addAttribute("error", "emptyinput");
        log.warn("AppExHandler: EmptyInputEx");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(InvalidPhoneNumberEx.class)
    public RedirectView handleInvalidPhoneNumber(Model model){
        model.addAttribute("error", "invalidphonenumber");
        log.warn("AppExHandler: InvalidPhoneNumberEx");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(IncorrectLoginEx.class)
    public RedirectView handleIncorrectLogin(Model model){
        model.addAttribute("error", "incorrectlogin");
        log.warn("AppExHandler: IncorrectLoginEx");
        return new RedirectView("/signin");
    }

    @ExceptionHandler(NoPostEx.class)
    public RedirectView handleNoPost(Model model){
        model.addAttribute("error", "nopost");
        log.warn("AppExHandler: NoPostEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(InvalidDateFormatEx.class)
    public RedirectView handleInvalidDateFormat(Model model){
        model.addAttribute("error", "invaliddateformat");
        log.warn("AppExHandler: InvalidDateFormat");
        return new RedirectView("/operation");
    }

    @ExceptionHandler(NoMatchedPostEx.class)
    public RedirectView handleMatchedPost(Model model){
        model.addAttribute("error", "matchedpost");
        log.warn("AppExHandler: NoMatchedPostEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(AdvEmptyInputEx.class)
    public RedirectView handleAdvEmptyInput(Model model){
        model.addAttribute("error", "advemptyinput");
        log.warn("AppExHandler: AdvEmptyInputEx");
        return new RedirectView("/index");
    }

    @ExceptionHandler(ChatEmptyInputEx.class)
    public RedirectView handleChatEmptyInput(Model model){
        model.addAttribute("error", "chatemptyinput");
        log.warn("AppExHandler: ChatEmptyInputEx");
        return new RedirectView("/chat");
    }

    @ExceptionHandler(FillInfoEmptyInputEx.class)
    public RedirectView handleFillInfoEmptyInput(Model model){
        model.addAttribute("error", "fillinfoemptyinput");
        log.warn("AppExHandler: FillInfoEmptyInputEx");
        return new RedirectView("/info");
    }

    @ExceptionHandler(PostEmptyInputEx.class)
    public RedirectView handlePostEmptyInput(Model model){
        log.warn("AppExHandler: PostEmptyInputEx");
        return new RedirectView("/myposts");
    }

    @ExceptionHandler(SignInEmptyInputEx.class)
    public RedirectView handleSignInEmptyInput(Model model){
        model.addAttribute("error", "signinemptyinput");
        log.warn("AppExHandler: SignInEmptyInputEx");
        return new RedirectView("/login");
    }

    @ExceptionHandler(SignUpEmptyInputEx.class)
    public RedirectView handleSignUpEmptyInput(Model model){
        model.addAttribute("error", "signupemptyinput");
        log.warn("AppExHandler: SignUpEmptyInputEx");
        return new RedirectView("/signup");
    }

    @ExceptionHandler(UpdateUserEmptyInputEx.class)
    public RedirectView handleUpdateUserEmptyInput(Model model){
        model.addAttribute("error", "updateuseremptyinput");
        log.warn("AppExHandler: UpdateUserEmptyInputEx");
        return new RedirectView("/update");
    }

    @ExceptionHandler(MessageEmptyInputEx.class)
    public RedirectView handleMessageEmptyInput(Model model){
        log.warn("AppExHandler: MessageEmptyInputEx");
        return new RedirectView("/message");
    }

    @ExceptionHandler(InvalidInputEx.class)
    public RedirectView handleInvalidInput(Model model){
        log.warn("AppExHandler: InvalidInputEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(NoParamEx.class)
    public RedirectView handleNoParam(){
        log.warn("AppExHandler: NoParamEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(NotAuthorizedEx.class)
    public RedirectView handleNotAuthorized(){
        log.warn("AppExHandler: NotAuthorizedEx");
        return new RedirectView("/myposts");
    }

    @ExceptionHandler(DuplicateWishedEx.class)
    public RedirectView handleDuplicateWished(){
        log.warn("AppExHandler: DuplicateWishedEx");
        return new RedirectView("/wishlist");
    }

    @ExceptionHandler(DuplicateBlockedEx.class)
    public RedirectView handleDuplicateBlocked(){
        log.warn("AppExHandler: DuplicateBlockedEx");
        return new RedirectView("/dashboard");
    }

    @ExceptionHandler(NotWishedEx.class)
    public RedirectView handleNotWished(){
        log.warn("AppExHandler: NotWishedEx");
        return new RedirectView("/wishlist");
    }

    @ExceptionHandler(SelfViewEx.class)
    public RedirectView handleSelfView(){
        log.warn("AppExHandler: SelfViewEx");
        return new RedirectView("/update");
    }

    @ExceptionHandler(MessageNotFoundEx.class)
    public RedirectView handleMessageNotFound(){
        log.warn("AppExHandler: MessageNotFoundEx");
        return new RedirectView("/dashboard");
    }


}
