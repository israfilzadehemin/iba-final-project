package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Blocked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "who_id")
    private Userr who;

    @ManyToOne
    @JoinColumn(name = "whom_id")
    private Userr whom;

    public Blocked(Userr loggedUser, Userr currentUser) {
        this.who = loggedUser;
        this.whom = currentUser;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Blocked{id=%d}", id);
    }
}
