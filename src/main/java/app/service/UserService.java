package app.service;

import app.exception.user.*;
import app.repo.UserRepo;
import app.tool.FileTool;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Log4j2
@Service
public class UserService {
  private final ValidationTool validationTool;
  private final FileTool fileTool;
  private final UserRepo userRepo;

  public UserService(ValidationTool validationTool, FileTool fileTool, UserRepo userRepo) {
    this.validationTool = validationTool;
    this.fileTool = fileTool;
    this.userRepo = userRepo;
  }

  public boolean register(String email, String pass, String passConfirm) {
    if (email.isBlank() || pass.isBlank() || passConfirm.isBlank()) {
      throw new EmptyInputEx();
    }
    else if (!validationTool.isEmailUnique(email)) {
      throw new EmailNotUniqueEx();
    }
    else if (!validationTool.passMatches(pass, passConfirm)) {
      throw new PassNotMatchEx();
    }
    else {
      log.info("User registered successfully");
      userRepo.addUser(String email, String pass);
    }
  }

  public boolean login(String login, String pass) {
    if (!validationTool.isLoginCorrect(login, pass)) {
      throw new IncorrectLoginEx();
    }
    else {
      return true;
    }
  }

  public boolean fillInfo(String username, String name, String surname, String city, String number, MultipartFile file) {
    if (username.isBlank() || name.isBlank() || surname.isBlank() ||
            city.isBlank() || number.isBlank() || file.isEmpty()) {
      throw new EmptyInputEx();
    }
    else if (!validationTool.isUsernameUnique(username)) {
      throw new UsernameNotUniqueEx();
    }
    else if(!validationTool.isPhoneValid(number)) {
      throw new InvalidPhoneNumberEx();
    }
    else {
      String image = fileTool.uploadImage(file);
      userRepo.fillUserInfo(String username, String name, String surname, String city, String number, String image);
    }
  }



}
