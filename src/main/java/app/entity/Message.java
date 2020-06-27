package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private Userr from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private Userr to;
}
