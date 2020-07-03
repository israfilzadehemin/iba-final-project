package app.service;

import app.entity.Category;
import app.entity.Post;
import app.entity.Wishlist;
import app.exception.input.PostEmptyInputEx;
import app.exception.post.InvalidInputEx;
import app.exception.post.NoPostEx;
import app.exception.post.NotAuthorizedEx;
import app.exception.post.PostNotFoundEx;
import app.repo.CategoryRepo;
import app.repo.PostRepo;
import app.repo.WishlistRepo;
import app.tool.ConverterTool;
import app.tool.FileTool;
import app.tool.ValidationTool;
import javafx.geometry.Pos;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class WishListService {
  private final PostRepo postRepo;
  private final UserService userService;
  private final WishlistRepo wishlistRepo;


  public WishListService(PostRepo postRepo, CategoryRepo categoryRepo, UserService userService, WishlistRepo wishlistRepo, ValidationTool validationTool, FileTool fileTool, ConverterTool converterTool) {
    this.postRepo = postRepo;
    this.userService = userService;
    this.wishlistRepo = wishlistRepo;
  }

  public List<Post> findWishlistedPosts(String userId) {
    List<Post> e = new ArrayList<>();
    List<Wishlist> all = wishlistRepo.findAll();
    List<Wishlist> collect = all.stream()
            .filter(wishlist -> wishlist.getUser().getId() == Long.parseLong(userId))
            .collect(Collectors.toList());

    return e;
  }

}
