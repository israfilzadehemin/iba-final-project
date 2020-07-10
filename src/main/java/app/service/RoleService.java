package app.service;

import app.entity.Blocked;
import app.entity.Role;
import app.entity.Userr;
import app.exception.post.InvalidInputEx;
import app.exception.user.DuplicateBlockedEx;
import app.repo.BlockedRepo;
import app.repo.RoleRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class RoleService {
  private final RoleRepo roleRepo;

  public Role findByRole(String role) {
    return roleRepo.findRolesByName(role).get(0);
  }

  public boolean addRoleToUser(Userr user, String roleName) {
    Role role = findByRole(roleName);
    role.getUsers().add(user);
    roleRepo.save(role);
    return true;
  }
}
