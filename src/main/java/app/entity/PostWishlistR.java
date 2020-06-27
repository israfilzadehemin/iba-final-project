package app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "post_wishlist")
public class PostWishlistR {
    @EmbeddedId
    private PostWishlistK id;

    @ManyToOne
    @MapsId("post_id")
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @MapsId("wishlist_id")
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;
}
