package app.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private long id;
    private String mail;
    private String fb_login;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String mobile;
    private String image;
    private LocalDateTime reg_date;
    private LocalDateTime last_seen;
    private String status;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @OneToOne(mappedBy = "user")
    private Wishlist wishlist;

    @OneToMany(mappedBy = "from")
    private Set<Message> from_messages;

    @OneToMany(mappedBy = "to")
    private Set<Message> to_messages;

    @OneToMany(mappedBy = "who")
    private Set<Blocked> who_block;

    @OneToMany(mappedBy = "whom")
    private Set<Blocked> whom_block;
}
