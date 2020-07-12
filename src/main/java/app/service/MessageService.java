package app.service;

import app.entity.Message;
import app.entity.Userr;
import app.exception.input.MessageEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.repo.MessageRepo;
import app.repo.UserRepo;
import app.tool.ValidationTool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class  MessageService {
  private final ValidationTool validationTool;
  private final MessageRepo messageRepo;
  private final UserService userService;

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

    return connections.stream()
            .map(c -> findMessagesBetween(loggedUserId, String.valueOf(c.getId())))
            .map(messages -> messages.get(messages.size() - 1))
            .collect(Collectors.toList());

  }

  public boolean sendMessage(String loggedUserId, String currentUserId, String text) {
    if (currentUserId == null || text == null || text.isBlank()) throw new MessageEmptyInputEx();
    else if (!validationTool.isParsableToLong(currentUserId)) throw new InvalidInputEx();
    else {
      messageRepo.save(
              new Message(
                      userService.findById(loggedUserId),
                      userService.findById(currentUserId),
                      text,
                      LocalDateTime.now()));
      return true;
    }
  }

}
