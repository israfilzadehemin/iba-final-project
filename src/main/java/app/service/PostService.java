package app.service;

import app.entity.Post;
import app.entity.Wishlist;
import app.repo.PostRepo;
import app.repo.WishlistRepo;
import app.tool.ValidationTool;
import javafx.geometry.Pos;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
  private final PostRepo postRepo;
  private final WishlistRepo wishlistRepo;
  private final ValidationTool validationTool;

  public PostService(PostRepo postRepo, WishlistRepo wishlistRepo, ValidationTool validationTool) {
    this.postRepo = postRepo;
    this.wishlistRepo = wishlistRepo;
    this.validationTool = validationTool;
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

  public List<Post> findFilteredPosts(String name, String category) {
    if (!validationTool.isCategoryValid(category)) {
      throw new InvalidPostCategoryEx();
    } else {
      List<Post> filteredPosts = postRepo.findAllbyNameAndCategory(name, Integer.parseInt(category));
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
}
