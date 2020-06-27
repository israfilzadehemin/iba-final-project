package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
