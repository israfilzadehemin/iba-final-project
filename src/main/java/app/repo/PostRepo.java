package app.repo;


import app.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("UPDATE Post p SET p.status=false WHERE p.id = :id")
    void deactivatePost(@Param("id") long id);

    @Query("UPDATE Post p SET p.name = :name, p.city = :city, p.image = :image WHERE p.id = :id")
    void updatePost(@Param("name") int name, @Param("city") int city,
                    @Param("image") int image, @Param("id") long id);
}
