package app.repo;


import app.entity.Userr;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Userr, Long> {
    Optional<Userr> getUserrByEmail(String email);
    Optional<Userr> getUserrByUsername(String username);

//    @Query("UPDATE Userr u SET u.username = :username, u.name = :name," +
//            "u.surname = :surname, u.city = :city," +
//            "u.phone = :phone, u.image = :image " +
//            "WHERE u.id = :id")
//    void fillUserInfo(@Param("username") String username, @Param("name") String name,
//                      @Param("surname") String surname, @Param("city") String city,
//                      @Param("phone") String phone, @Param("image") String image,
//                      @Param("id") Long id);


    Optional<Userr> findUserrByEmail(String email);

    Optional<Userr> findUserrByUsername(String username);
}

