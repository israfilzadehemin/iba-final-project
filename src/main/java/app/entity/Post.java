package app.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
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
    private LocalDateTime expiry_data;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "u_id")
    private Userr user;

    @OneToMany(mappedBy = "post")
    Set<PostWishlistR> postsInWishLists;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "c_id")
    private Category category;


}
