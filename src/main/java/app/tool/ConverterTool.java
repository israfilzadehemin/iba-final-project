package app.tool;

import app.exception.post.InvalidDateFormatEx;
import app.repo.PostRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@Service
public class ConverterTool {
  private final PostRepo postRepo;

  public ConverterTool(PostRepo postRepo) {
    this.postRepo = postRepo;
  }

  public LocalDate stringToLocalDate (String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
      return LocalDate.parse(date, formatter);
    } catch (Exception e) {
      throw new InvalidDateFormatEx();
    }
  }
}
