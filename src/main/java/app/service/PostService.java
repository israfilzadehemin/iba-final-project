package app.service;

import app.entity.Category;
import app.entity.Post;
import app.exception.post.NotAuthorizedEx;
import app.exception.input.PostEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.exception.post.NoPostEx;
import app.exception.post.PostNotFoundEx;
import app.repo.CategoryRepo;
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
  private final CategoryRepo categoryRepo;
  private final WishlistRepo wishlistRepo;
  private final ValidationTool validationTool;
  private final FileTool fileTool;
  private final ConverterTool converterTool;


  public PostService(PostRepo postRepo, CategoryRepo categoryRepo, WishlistRepo wishlistRepo, ValidationTool validationTool, FileTool fileTool, ConverterTool converterTool) {
    this.postRepo = postRepo;
    this.categoryRepo = categoryRepo;
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

  public boolean addOrUpdate(String id, String name, String category, String city, String date, MultipartFile file) {
    if (id.isBlank() || name.isBlank() || category.isBlank() || city.isBlank() || date.isBlank() || file.isEmpty()) throw new PostEmptyInputEx();
    else if (!validationTool.isParsableToLong(id)) throw new InvalidInputEx();
    else if (!validationTool.isCategoryValid(category)) throw new InvalidInputEx();
    else {
      LocalDate parsedDate = converterTool.stringToLocalDate2(date);
      String image = fileTool.uploadPostImage(file);
      if (id.equals(0)) {
        Category cat = categoryRepo.findById(Long.parseLong(category)).get();
        Post post = new Post(name, cat, city, image, parsedDate);
        postRepo.save(post);
        log.info("Post added successfully");
        return true;
      } else {
        Category cat = categoryRepo.findById(Long.parseLong(category)).get();
        Post post = findById(id);
        post.setName(name);
        post.setCategory(cat);
        post.setCity(city);
        post.setExpiry_date(parsedDate);
        post.setImage(fileTool.uploadPostImage(file));
        postRepo.save(post);
        log.info("Post updated successfully");
        return true;
      }

    }
  }

  public List<Post> findFiltered(String name, String category) {
    if (!validationTool.isParsableToLong(category)) {
      throw new InvalidInputEx();
    } else {
      List<Post> filteredPosts = postRepo.findAllByNameContainingAndCategory_Id(name, Long.parseLong(category));
      if (filteredPosts.size() == 0) {
        throw new PostNotFoundEx();
      } else {
        return filteredPosts;
      }
    }
  }

//  public List<Post> findWishlisted(String userId) {
//should be asked from Ayshan
//  }
//
  public List<Post> findByUser(String userId) {
    return postRepo.findPostsByUserId(Long.parseLong(userId));
  }

  public boolean isAuthorized(String userId, String postId) {
    System.err.println(postId);
    if (!validationTool.isParsableToLong(postId)) throw new InvalidInputEx();

    Post post = findById(postId);

    if (post.getUser().getId()==Long.parseLong(userId)) return true;
    else {
      log.warn("Current user is not authorized to edit this post: from PostService.isAuthorized()");
      throw new NotAuthorizedEx();
    }
  }
}
