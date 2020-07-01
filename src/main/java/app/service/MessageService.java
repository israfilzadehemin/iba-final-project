package app.service;

import app.entity.Message;
import app.entity.Userr;
import app.repo.MessageRepo;
import app.repo.UserRepo;
import app.tool.FileTool;
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
  private final UserRepo userRepo;

  public MessageService(ValidationTool validationTool, UserRepo userRepo, MessageRepo messageRepo) {
    this.validationTool = validationTool;
    this.messageRepo = messageRepo;
    this.userRepo = userRepo;
  }

//  public HashMap<Userr, String> getConnectedUsers(String userId) {
//    Integer userIdParsed = Integer.parseInt(userId);
//    HashMap<Userr, String> messagesByUser = new HashMap<Userr, String>();
//
//    List<Message> sent = messageRepo.findAllbyFromId(userId);
//    List<Message> received = messageRepo.findAllbyToId(userId);
//    List<Message> allMessages = new ArrayList<>();
//
//    allMessages.addAll(sent);
//    allMessages.addAll(received);
//
//    Comparator<Message> compareById = Comparator.comparing(Message::getId).reversed();
//    allMessages.sort(compareById);
//
//    allMessages.forEach(m -> {
//      if (m.getFrom().getId() != userIdParsed) messagesByUser.put(m.getFrom(), m.getMessage());
//      if (m.getTo().getId() != userIdParsed) messagesByUser.put(m.getTo(), m.getMessage());
//    });
//
//    return messagesByUser;
//  }
//
//  public List<Message> getAllMessagesbyUser(String me, String other) {
//
//    User meById = userRepo.findById(Long.parseLong(me)).get();
//    User otherById = userRepo.findById(Long.parseLong(other)).get();
//
//    List<Message> sent = messageRepo.findAllbyFromIdAndToId(meById, otherById);
//    List<Message> received = messageRepo.findAllbyFromIdAndToId(otherById, meById);
//    List<Message> allMessages = new ArrayList<>();
//
//    allMessages.addAll(sent);
//    allMessages.addAll(received);
//
//    Comparator<Message> compareById = Comparator.comparing(Message::getId);
//    allMessages.sort(compareById);
//
//    return allMessages;
//  }
//
//  public List<Message> getLastMessagesbyUser(String me, String other) {
//
//    List<Message> allMessages = getAllMessagesbyUser(me, other);
//
//    Collections.reverse(allMessages);
//    List<Message> limited = allMessages.stream().limit(10).collect(Collectors.toList());
//
//    return limited;
//  }

}
