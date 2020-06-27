package app.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PostWishlistK implements Serializable {
    @Column(name = "post_id")
    private long postId;
    @Column(name = "wishlist_id")
    private long wishlistId;
}
