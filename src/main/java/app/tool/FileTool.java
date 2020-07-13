package app.tool;

import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
@Service
public class FileTool {
  private final UserRepo userRepo;
  private static final String PROFILE_PHOTOS = "uploaded/profile/";
  private static final String POST_IMAGE = "uploaded/post/";

  public FileTool(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public String uploadProfilePic(MultipartFile file, String username) {
    return uploadImage(username, file, PROFILE_PHOTOS);
  }

  public String uploadPostImage(MultipartFile file, String fileName) {
    return uploadImage(fileName, file, POST_IMAGE);
  }

  private String uploadImage(String name, MultipartFile file, String source) {
    try {
      LocalDateTime date = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
      String dateFormatted = date.format(formatter);
      String fileName = String.format("%s%s%s.jpg", source, name.replaceAll(" ", "").toLowerCase(), dateFormatted);
      String type = file.getContentType();
      byte[] bytes = file.getBytes();
      Path path = Paths.get(fileName);
      Files.write(path, bytes);
      log.info("Profile photo uploaded successfully");
      return fileName;

    } catch (IOException e) {
      log.warn("IOException caught while photo uploading");
      return "error";
    }
  }
}
