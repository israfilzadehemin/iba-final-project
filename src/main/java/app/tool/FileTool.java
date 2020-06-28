package app.tool;

import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
@Service
public class FileTool {
  private final UserRepo userRepo;
  private static final String PROFILE_PHOTOS = "classpath:/static/img/profile/";
  private static final String POST_IMAGE = "classpath:/static/img/post/";

  public FileTool(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public String uploadProfilePic(MultipartFile file) {
    return uploadImage(file, PROFILE_PHOTOS);
  }

  public String uploadPostImage(MultipartFile file) {
    return uploadImage(file, POST_IMAGE);
  }

  private String uploadImage(MultipartFile file, String source) {
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(source +file.getOriginalFilename());
      Files.write(path, bytes);
      log.info("Profile photo uploaded successfully");
      return file.getOriginalFilename();

    } catch (IOException e) {
      log.warn("IOException caught while profile photo uploading");
      return "error";
    }
  }
}
