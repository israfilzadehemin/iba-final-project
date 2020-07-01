package app.repo;


import app.entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    @Query("UPDATE Post p SET p.status=false WHERE p.id = :id")
    void deactivatePost(@Param("id") long id);

    @Query("UPDATE Post p SET p.name = :name, p.city = :city, p.image = :image, p.expiry_date =:expiry_date WHERE p.id = :id")
    void updatePost(@Param("name") String name, @Param("city") String city,
                    @Param("image") String image, @Param("expiry_date")LocalDate expiry_date, @Param("id") long id);

    List<Post> findAllByNameContainingAndCategory_Id(String name, Long categoryId);

}
