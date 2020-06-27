package app.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
  public boolean checkDuplicate(String mail, String username) {
    return false;
  }
}
