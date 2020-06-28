package app.service;

import app.entity.Post;
import app.exception.post.EmptyInputEx;
import app.exception.post.PostNotFoundEx;
import app.repo.PostRepo;
import app.repo.WishlistRepo;
import app.tool.ConverterTool;
import app.tool.FileTool;
import app.tool.ValidationTool;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

  public List<Post> findAllPosts() {
    List<Post> allPosts = postRepo.findAll();
    if (allPosts.size() == 0) {
      throw new NoPostEx();
    } else {
      return allPosts;
    }
  }

  public Post findPostById(String id) {
    if (!validationTool.isParsableToLong(id))
      throw new InvalidInputEx();
    else {
      Optional<Post> post = postRepo.findById(Long.parseLong(id));
      if (post.equals(Optional.empty())) throw new PostNotFoundEx();
      else return post.get();
    }
  }

  public void deactivatePost(String id) {
    if (!validationTool.isParsableToLong(id))
      throw new InvalidInputEx();
    else {
      Optional<Post> post = postRepo.findById(Long.parseLong(id));
      if (post.equals(Optional.empty())) throw new PostNotFoundEx();
      else postRepo.deactivatePost( long id);
    }

  }

  public void addOrUpdatePost(String id, String name, String city, String date, MultipartFile file) {
    if (id.isBlank() || name.isBlank() ||
            city.isBlank() || date.isBlank() || file.isEmpty())
      throw new EmptyInputEx();

    else if (!validationTool.isParsableToLong(id)) throw new InvalidInputEx();

    else {
      LocalDate parsedDate = converterTool.stringToLocalDate(date);
      String image = fileTool.uploadPostImage(file);
      if (id.equals(0)) {
        postRepo.addPost(name, city, parsedDate, image);
      } else {
        Post postById = findPostById(id);
        postRepo.updatePost(postById.getId(), name, city, parsedDate, image);
      }

    }
  }

  public List<Post> findFilteredPosts(String name, String category) {
    if (!validationTool.isParsableToLong(category)) {
      throw new InvalidInputEx();
    } else {
      List<Post> filteredPosts = postRepo.findAllbyNameAndCategory(name, Long.parseLong(category));
      if (filteredPosts.size() == 0) {
        throw new NoPostEx();
      } else {
        return filteredPosts;
      }
    }
  }

  public List<Post> findWishlistPosts(String userId) {
//should be asked from Ayshan
  }

  public List<Post> findPostsByUser(String userId) {
    return postRepo.findPostsByUserId(Integer.parseInt(userId));
  }
}
