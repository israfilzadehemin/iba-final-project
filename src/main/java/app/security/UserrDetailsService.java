package app.security;

import app.entity.Userr;
import app.repo.UserRepo;
import app.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Configuration
@AllArgsConstructor
public class UserrDetailsService implements UserDetailsService {

  private final UserService userService;

  public static UserDetails userToUserDetails(Userr user) {
    return User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .roles()
            .build();
  }

  public static UserDetails userToUserrDetails(Userr user) {
    return new UserrDetails(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getRoles().stream()
                    .map(r -> new GrantedAuthority() {
                      @Override
                      public String getAuthority() {
                        return r.toString();
                      }

                    }).collect(Collectors.toSet()),
            true,
            true,
            true,
            true

    );
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return Optional.of(userService.findByEmail(email))
            .map(UserrDetailsService::userToUserrDetails).get();
  }
}
