package app.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  public boolean register(String email, String password, String conPass) {
    return false;
  }

  public boolean login(String login, String pass) {
    return false;
  }

  public boolean fillInfo(String username, String name, String surname, String city, String image, String number) {
    return false;
  }
}
