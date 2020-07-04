package app.service;

import app.entity.Userr;
import app.entity.Userr;
import app.exception.input.EmptyInputEx;
import app.exception.input.FillInfoEmptyInputEx;
import app.exception.input.SignUpEmptyInputEx;
import app.exception.input.UpdateUserEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.exception.user.*;
import app.repo.UserRepo;
import app.tool.FileTool;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

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
      throw new SignUpEmptyInputEx();
    } else if (!validationTool.isEmailUnique(email)) {
      throw new EmailNotUniqueEx();
    } else if (!validationTool.passMatches(pass, passConfirm)) {
      throw new PassNotMatchEx();
    } else {
      log.info("User registered successfully");
      Userr user = new Userr(email, pass);
      userRepo.save(user);
      return true;
    }
  }

  public boolean login(String login, String pass) {
    if (!validationTool.isLoginCorrect(login, pass)) {
      throw new IncorrectLoginEx();
    } else {
      return true;
    }
  }

  public boolean fillInfo(String id, String username, String name, String surname, String city, String number, MultipartFile file) {
    if (username.isBlank() || name.isBlank() || surname.isBlank() ||
            city.isBlank() || number.isBlank() || file.isEmpty()) {
      throw new FillInfoEmptyInputEx();
    } else if (!validationTool.isUsernameUnique(username)) {
      throw new UsernameNotUniqueEx();
    } else if (!validationTool.isPhoneValid(number)) {
      throw new InvalidPhoneNumberEx();
    } else {
      String image = fileTool.uploadProfilePic(file);
      Userr user = findById(id);
      user.setUsername(username);
      user.setName(name);
      user.setSurname(surname);
      user.setCity(city);
      user.setPhone(number);
      user.setImage(image);
      userRepo.save(user);
      log.info("User info filled successfully");
      return true;
    }
  }

  public boolean updateUser(String id, String name, String surname, String city, String number, MultipartFile file) {
    if (name == null || surname == null || city == null
            || number == null || id.isBlank() || name.isBlank()
            || surname.isBlank() || city.isBlank() || number.isBlank()
            || file.isEmpty()
    ) {
      throw new UpdateUserEmptyInputEx();
    } else if (!validationTool.isPhoneValid(number)) {
      throw new InvalidPhoneNumberEx();
    } else {
      String image = fileTool.uploadProfilePic(file);
      Userr user = findById(id);
      user.setName(name);
      user.setSurname(surname);
      user.setCity(city);
      user.setPhone(number);
      user.setImage(image);
      userRepo.save(user);
      log.info("User profile updated successfully");
      return true;
    }
  }

  public Userr findById(String id) {
    if (validationTool.isParsableToLong(id))
      return userRepo.findById(Long.parseLong(id)).orElseThrow(UserNotFoundEx::new);
    else throw new InvalidInputEx();
  }

  public Userr viewUser(String userId, String loggedId) {
    Userr user = findById(userId);
    if (user.getId()==Long.parseLong(loggedId)) throw new SelfViewEx();
    return user;
  }
}
