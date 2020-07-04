package app.service;

import app.entity.Blocked;
import app.entity.Userr;
import app.exception.post.InvalidInputEx;
import app.exception.user.DuplicateBlockedEx;
import app.repo.BlockedRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BlockedService {
  private final BlockedRepo blockedRepo;
  private final UserService userService;

  public BlockedService(BlockedRepo blockedRepo, UserService userService) {
    this.blockedRepo = blockedRepo;
    this.userService = userService;
  }

  public boolean addBlocked(String loggedUserId, String currentUserId) {
    if (findBlockedUsers(loggedUserId).contains(userService.findById(currentUserId))) throw new DuplicateBlockedEx();
    if (loggedUserId.equals(currentUserId)) throw new InvalidInputEx();
    blockedRepo.save(new Blocked(userService.findById(loggedUserId), userService.findById(currentUserId)));
    return true;
  }

  public List<Userr> findBlockedUsers(String loggedUserId) {
    List<Blocked> blockeds = blockedRepo.findByWho(userService.findById(loggedUserId));

    return blockeds.stream()
            .map(Blocked::getWhom)
            .collect(Collectors.toList());
  }
}
