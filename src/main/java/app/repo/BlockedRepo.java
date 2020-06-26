package app.repo;


import app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedRepo extends JpaRepository<Post, Long> {
}
