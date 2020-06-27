package app.tool;

import app.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.jni.File;
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

  public FileTool(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public String uploadImage(MultipartFile file) {
    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(PROFILE_PHOTOS+file.getOriginalFilename());
      Files.write(path, bytes);
      log.info("Profile photo uploaded successfully");
      return file.getOriginalFilename();

    } catch (IOException e) {
      log.warn("IOException caught while profile photo uploading");
      return "error";
    }
  }
}
