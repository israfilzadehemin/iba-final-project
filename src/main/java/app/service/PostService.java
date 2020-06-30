package app.service;

import app.entity.Post;
import app.exception.input.PostEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.exception.post.NoPostEx;
import app.exception.post.PostNotFoundEx;
import app.repo.PostRepo;
import app.repo.WishlistRepo;
import app.tool.ConverterTool;
import app.tool.FileTool;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PostService {
  private final PostRepo postRepo;
  private final WishlistRepo wishlistRepo;
  private final ValidationTool validationTool;
  private final FileTool fileTool;
  private final ConverterTool converterTool;


  public PostService(PostRepo postRepo, WishlistRepo wishlistRepo, ValidationTool validationTool, FileTool fileTool, ConverterTool converterTool) {
    this.postRepo = postRepo;
    this.wishlistRepo = wishlistRepo;
    this.validationTool = validationTool;
    this.fileTool = fileTool;
    this.converterTool = converterTool;
  }

  public void fillAdver(String fullName, String number, String time) {
//should be implemented for index
  }

  public List<Post> findAll() {
    List<Post> allPosts = postRepo.findAll();
    if (allPosts.size() == 0) {
      throw new NoPostEx();
    } else {
      return allPosts;
    }
  }

  public Post findById(String id) {
    if (!validationTool.isParsableToLong(id))
      throw new InvalidInputEx();
    else {
      return postRepo.findById(Long.parseLong(id)).orElseThrow(PostNotFoundEx::new);
    }
  }

  public boolean deactivate(String id) {
    if (!validationTool.isParsableToLong(id))
      throw new InvalidInputEx();
    else {
      Optional<Post> post = postRepo.findById(Long.parseLong(id));
      if (post.equals(Optional.empty())) throw new PostNotFoundEx();
      else postRepo.deactivatePost(Long.parseLong(id));
      log.info("Post deactivated successfully");
      return true;
    }

  }

  public boolean addOrUpdate(String id, String name, String city, String date, MultipartFile file) {
    if (id.isBlank() || name.isBlank() || city.isBlank() || date.isBlank() || file.isEmpty()) throw new PostEmptyInputEx();
    else if (!validationTool.isParsableToLong(id)) throw new InvalidInputEx();
    else {
      LocalDate parsedDate = converterTool.stringToLocalDate(date);
      String image = fileTool.uploadPostImage(file);
      if (id.equals(0)) {
        Post post = new Post(name, city, image, parsedDate);
        postRepo.save(post);
        log.info("Post added successfully");
        return true;
      } else {
        Post postById = findById(id);
        postRepo.updatePost(name, city, image, parsedDate,Long.parseLong(id));
        log.info("Post updated successfully");
        return true;
      }

    }
  }

//  public List<Post> findFiltered(String name, String category) {
//    if (!validationTool.isParsableToLong(category)) {
//      throw new InvalidInputEx();
//    } else {
//      List<Post> filteredPosts = postRepo.findAllbyNameAndCategory(name, Long.parseLong(category));
//      if (filteredPosts.size() == 0) {
//        throw new NoPostEx();
//      } else {
//        return filteredPosts;
//      }
//    }
//  }

//  public List<Post> findWishlisted(String userId) {
//should be asked from Ayshan
//  }

//  public List<Post> findByUser(String userId) {
//    return postRepo.findPostsByUserId(Integer.parseInt(userId));
//  }
}
