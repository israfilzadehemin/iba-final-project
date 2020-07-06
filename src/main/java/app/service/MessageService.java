package app.service;

import app.entity.Message;
import app.entity.Userr;
import app.repo.MessageRepo;
import app.repo.UserRepo;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

  public void getMessagesBetween(String loggedUserId, String currentUser) {
    System.out.println(messageRepo.findAll());
  }

  public List<Message> getAllMessagesbyUser(String me, String other) {

    Userr meById = userRepo.findById(Long.parseLong(me)).get();
    System.out.println("sender: "+meById);
    Userr otherById = userRepo.findById(Long.parseLong(other)).get();
    System.out.println("receiver:" +otherById);

    List<Message> sent = messageRepo.findAllByFromAndTo(meById, otherById);
    List<Message> received = messageRepo.findAllByFromAndTo(otherById, meById);
    List<Message> allMessages = new ArrayList<>();

    allMessages.addAll(sent);
    allMessages.addAll(received);

    Comparator<Message> compareById = Comparator.comparing(Message::getId);
    allMessages.sort(compareById);

    return allMessages;
  }

  public List<Message> getLastMessagesbyUser(String me, String other) {

    List<Message> allMessages = getAllMessagesbyUser(me, other);

    Collections.reverse(allMessages);
    List<Message> limited = allMessages.stream().limit(10).collect(Collectors.toList());

    return limited;
  }

}
