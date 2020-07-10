package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private int id;
    private String roles;

    @ManyToOne
    @JoinTable(name = "r_role_user",
            joinColumns = {@JoinColumn(name="role_id",
                    referencedColumnName = "r_id"),
            },
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    referencedColumnName = "u_id")})
    private Userr userr;

    @Override
    public String toString() {
        return roles;
    }


}
