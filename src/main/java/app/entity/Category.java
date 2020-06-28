package app.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private long id;
    private String name;

    @OneToMany(mappedBy = "category")
    Set<Post> posts;
}
