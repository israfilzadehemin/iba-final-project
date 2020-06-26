package app.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="p_id")
    private long id;
    private String name;
    private String category;
    private String city;
    private String image;
    private String status;
    private LocalDateTime expiry_data;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "u_id")
    private Userr user;

    @ManyToOne
    @JoinColumn(name="wishlist_id",referencedColumnName = "w_id")
    private Wishlist wishlist;
}
