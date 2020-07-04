package app.service;

import app.entity.Post;
import app.entity.Wished;
import app.exception.post.DuplicateWishedEx;
import app.exception.post.InvalidInputEx;
import app.exception.post.NotWishedEx;
import app.exception.post.PostNotFoundEx;
import app.repo.WishedRepo;
import app.tool.ValidationTool;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class WishedService {
  private final WishedRepo wishedRepo;
  private final PostService postService;
  private final ValidationTool validationTool;

  public WishedService(WishedRepo wishedRepo, PostService postService, ValidationTool validationTool) {
    this.wishedRepo = wishedRepo;
    this.postService = postService;
    this.validationTool = validationTool;
  }


  public List<Post> findWishedPosts(String userId) {
    List<Post> posts = new ArrayList<>();
    List<Wished> wisheds = wishedRepo.findAllByUserr(Long.parseLong(userId));
    wisheds.forEach(w -> posts.add(postService.findById(String.valueOf(w.getPost()))));
    return posts;
  }

  public long findCurrentWishedPostId(String userId, String postId) {
    if (!validationTool.isParsableToLong(postId)) throw new InvalidInputEx();
    return wishedRepo.findByUserrAndPost(Long.parseLong(userId), Long.parseLong(postId))
            .orElseThrow(PostNotFoundEx::new).getPost();
  }

  public List<Post> findFilteredWishedPosts(String userId, String name, String category) {
    return findWishedPosts(userId).stream()
            .filter(w -> w.getName().toLowerCase().contains(name.toLowerCase())
                    && w.getCategory().getId()==Long.parseLong(category))
            .collect(Collectors.toList());
  }

  public boolean addWished(String userId, String postId) {
    if (!validationTool.isParsableToLong(postId)) throw new InvalidInputEx();
    else if (findWishedPosts(userId).stream()
            .anyMatch(w -> w.getId()==Long.parseLong(postId))) throw new DuplicateWishedEx();
    wishedRepo.save(new Wished(Long.parseLong(userId), Long.parseLong(postId)));
    return true;
  }

  public boolean deleteWished(String userId, String postId) {
    if (!validationTool.isParsableToLong(postId)) throw new InvalidInputEx();
    else if (findWishedPosts(userId).stream()
            .noneMatch(w -> w.getId()==Long.parseLong(postId))) throw new NotWishedEx();
    wishedRepo.deleteByPostAndUserr(Long.parseLong(postId), Long.parseLong(userId));
    return true;
  }

}
