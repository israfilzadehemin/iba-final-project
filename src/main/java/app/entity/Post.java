package app.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id")
    private long id;
    private String name;
    private String city;
    private String image;
    private boolean status;
    private LocalDate expiry_date;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "u_id")
    private Userr user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "c_id")
    private Category category;

    public Post(Userr user,String name, Category category, String city, String image, LocalDate expiry_date) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.city = city;
        this.image = image;
        this.expiry_date = expiry_date;
        this.status = true;
        this.date = LocalDate.now();
    }


}
