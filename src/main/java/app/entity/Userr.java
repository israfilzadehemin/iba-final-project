package app.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private long id;
    private String email;
    private String fb_login;
    private String username;
    private String password;
    private String passConf;
    private String name;
    private String surname;
    private String city;
    private String phone;
    private String image;
    private LocalDateTime reg_date;
    private LocalDateTime last_seen;
    private String status;

    public Userr(String email, String password){
        this.email=email;
        this.password=password;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> posts;

    @OneToMany(mappedBy = "from", fetch = FetchType.LAZY)
    private Set<Message> from_messages;

    @OneToMany(mappedBy = "to", fetch = FetchType.LAZY)
    private Set<Message> to_messages;

    @OneToMany(mappedBy = "who")
    private Set<Blocked> who_block;

    @OneToMany(mappedBy = "whom")
    private Set<Blocked> whom_block;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userr userr = (Userr) o;
        return id == userr.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Userr{id=%d}", id);
    }
}
