package app.service;

import app.entity.Role;
import app.entity.Userr;
import app.exception.input.*;
import app.exception.post.InvalidInputEx;
import app.exception.user.*;
import app.repo.UserRepo;
import app.tool.FileTool;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service
public class UserService {
  private final ValidationTool validationTool;
  private final FileTool fileTool;
  private final UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;
  private final RoleService roleService;

  public UserService(ValidationTool validationTool,
                     FileTool fileTool,
                     UserRepo userRepo,
                     PasswordEncoder passwordEncoder,
                     RoleService roleService) {
    this.validationTool = validationTool;
    this.fileTool = fileTool;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.roleService = roleService;
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
      Userr user = new Userr(email.toLowerCase(), passwordEncoder.encode(pass), LocalDateTime.now(), true);
      userRepo.save(user);
      roleService.addRoleToUser(user, "USER");
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

  public boolean isInfoFilled(long loggedUserId) {
    Userr user = findById(String.valueOf(loggedUserId));
    if (user.getCity() == null || user.getName() == null ||
            user.getSurname() == null || user.getUsername() == null) return false;

    return true;
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
      user.setUsername(username.toLowerCase());
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


  public boolean updatePassword(String email, String pass, String conPass) {
    if (email == null || pass == null || conPass == null
            || email.isBlank() || pass.isBlank() || conPass.isBlank()) throw new ResetEmptyInputEx();
    else if (!pass.equals(conPass)) throw new NewPassNotMatchEx();
    else {
      Userr user = findByEmail(email);
      user.setPassword(passwordEncoder.encode(pass));
      userRepo.save(user);
      return true;
    }
  }

  public Userr findById(String id) {
    if (validationTool.isParsableToLong(id)) {
      return userRepo.findById(Long.parseLong(id)).orElseThrow(UserNotFoundEx::new);
    } else throw new InvalidInputEx();
  }

  public Userr findByEmail(String email) {
    return userRepo.findUserrByEmail(email).orElseThrow(UserNotFoundEx::new);
  }

  public Userr viewUser(String userId, String loggedId) {
    Userr user = findById(userId);
    if (user.getId() == Long.parseLong(loggedId)) throw new SelfViewEx();
    return user;
  }

  public boolean isUserExistByEmail(String mail) {
    return userRepo.getUserrByEmail(mail).isPresent();
  }
}
