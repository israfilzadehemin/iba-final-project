package app.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="w_id")
    private long id;

    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "u_id")
    private Userr user;

    @OneToMany(mappedBy = "post")
    Set<PostWishlistR> postsInWishlists;
}
