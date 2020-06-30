package app.tool;

import app.entity.Userr;
import app.repo.PostRepo;
import app.repo.UserRepo;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Log4j2
@Service
public class ValidationTool {
  private final UserRepo userRepo;
  private final PostRepo postRepo;


  public ValidationTool(UserRepo userRepo, PostRepo postRepo) {
    this.userRepo = userRepo;
    this.postRepo = postRepo;
  }

  public boolean isEmailUnique(String email) {
    Optional<Userr> user = userRepo.findUserrByEmail(email);
    if (user.equals(Optional.empty())) {
      return true;
    } else {
      log.warn("Email is not unique: from ValidationTool.isEmailUnique() ");
      return false;
    }
  }

  public boolean passMatches(String pass, String passConfirm) {
    if (pass.equals(passConfirm)) return true;
    else {
      log.warn("Password and Confirm password is not same: from ValidationTool.passmatches()");
      return false;
    }
  }

  public boolean isUsernameUnique(String username) {
    Optional<Userr> user = userRepo.findUserrByUsername(username);
    if (user.equals(Optional.empty())) {
      return true;
    } else {
      log.warn("Email is not unique: from ValidationTool.isUsernameUnique() ");
      return false;
    }
  }

  public boolean isPhoneValid(String number) {
    if (!isAzercell(number) && !isBakcell(number) && !isNar(number) && !isHome(number)) {
      log.warn("Phone number is not valid: from ValidationTool.isPhoneValid()");
      return false;
    } else {
      return true;
    }
  }

  public boolean isAzercell(String number) {
    try {
      long parsedNumber = Long.parseLong(number);
      if ((parsedNumber > 502000000 && parsedNumber < 509999999)
              || (parsedNumber > 512000000 && parsedNumber < 519999999)) {
        log.info("Azercell number registered");
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      log.warn("Number could not be parsed: from ValidationTool.isAzercell()");
      return false;
    }
  }

  public boolean isBakcell(String number) {
    try {
      long parsedNumber = Long.parseLong(number);
      if ((parsedNumber > 552000000 && parsedNumber < 559999999)
              || (parsedNumber > 992000000 && parsedNumber < 999999999)) {
        log.info("Bakcell number registered");
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      log.warn("Number could not be parsed: from ValidationTool.isBakcell()");
      return false;
    }
  }

  public boolean isNar(String number) {
    try {
      long parsedNumber = Long.parseLong(number);
      if ((parsedNumber > 702000000 && parsedNumber < 709999999)
              || (parsedNumber > 772000000 && parsedNumber < 779999999)) {
        log.info("Nar number registered");
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      log.warn("Number could not be parsed: from ValidationTool.isNar()");
      return false;
    }
  }

  public boolean isHome(String number) {
    try {
      long parsedNumber = Long.parseLong(number);
      if (parsedNumber > 122000000 && parsedNumber < 129999999) {
        log.info("Home number registered");
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      log.warn("Number could not be parsed: from ValidationTool.isHome()");
      return false;
    }
  }

  public boolean isLoginCorrect(String login, String pass) {
    Optional<Userr> userByEmail = userRepo.findUserrByEmail(login);
    Optional<Userr> userByUsername = userRepo.findUserrByUsername(login);

    if (!userByEmail.equals(Optional.empty())) {
      if (userByEmail.get().getPassword().equals(pass)) {
        return true;
      } else {
        log.warn("Password is not correct: from ValidationTool.isLoginCorrect()");
        return false;
      }
    } else if (!userByUsername.equals(Optional.empty())) {
      if (userByUsername.get().getPassword().equals(pass)) {
        return true;
      } else {
        log.warn("Password is not correct: from ValidationTool.isLoginCorrect()");
        return false;
      }
    } else {
      log.warn("Username and email is not found: from ValidationTool.isLoginCorrect()");
      return false;
    }
  }

  public boolean isParsableToLong(String source) {
    try {
      Long.parseLong(source);
      return true;
    } catch (NumberFormatException e) {
      log.warn("Value could not be parsed to Long: from ValidationTool.isParsableToLong()");
      return false;
    }
  }


}