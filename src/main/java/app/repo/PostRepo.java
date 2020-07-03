package app.repo;


import app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findAllByStatusAndIdIsNot(boolean status, long id);

    List<Post> findAllByNameContainingAndCategory_IdAndStatus(String name, Long categoryId, boolean status);

    List<Post> findPostsByUserIdAndStatus(long userId, boolean status);

}
