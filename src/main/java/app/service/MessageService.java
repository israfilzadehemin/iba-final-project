package app.service;

import app.entity.Message;
import app.entity.Userr;
import app.repo.MessageRepo;
import app.repo.UserRepo;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MessageService {
  private final ValidationTool validationTool;
  private final MessageRepo messageRepo;
  private final UserService userService;
  private final UserRepo userRepo;

  public MessageService(ValidationTool validationTool, MessageRepo messageRepo, UserService userService, UserRepo userRepo) {
    this.validationTool = validationTool;
    this.messageRepo = messageRepo;
    this.userService = userService;
    this.userRepo = userRepo;

  }


  public List<Message> findMessagesBetween(String loggedUserId, String currentUserId) {

    Userr loggedUser = userService.findById(loggedUserId);
    Userr currentUser = userService.findById(currentUserId);

    List<Message> sent = messageRepo.findAllByFromAndTo(loggedUser, currentUser);
    List<Message> received = messageRepo.findAllByFromAndTo(currentUser, loggedUser);
    List<Message> allMessages = new ArrayList<>();

    allMessages.addAll(sent);
    allMessages.addAll(received);

    allMessages.sort(Comparator.comparing(Message::getId));

    return allMessages;
  }

  public List<Message> findLastMessagesbyUser(String loggedUserId) {
    Userr loggedUser = userService.findById(loggedUserId);
    List<Message> allMessages = messageRepo.findAllByFromOrTo(loggedUser, loggedUser);

    Set<Userr> connections = new HashSet<>();

    allMessages.forEach(m -> {
      if (m.getFrom().equals(loggedUser)) connections.add(m.getTo());
      else connections.add(m.getFrom());
    });

    List<Message> lastMessages = connections.stream()
            .map(c -> findMessagesBetween(loggedUserId, String.valueOf(c.getId())))
            .map(messages -> messages.get(messages.size() - 1))
            .collect(Collectors.toList());

    return lastMessages;
  }

}
