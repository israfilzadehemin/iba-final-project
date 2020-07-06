package app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "m_id")
    private long id;
    private String message;
    private LocalDateTime date;

    @ManyToOne
    @JoinTable(name = "r_from_mes",
            joinColumns = {@JoinColumn(name="message_id",
                    referencedColumnName = "m_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "from_id",
                    referencedColumnName = "u_id")})
    private Userr from;

    @ManyToOne
    @JoinTable(name = "r_to_mes",
            joinColumns = {@JoinColumn(name="message_id",
                    referencedColumnName = "m_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "to_id",
                    referencedColumnName = "u_id")})
    private Userr to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Message{id=%d}", id);
    }
}
