package app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Wished {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "w_id")
  private long id;

  private long userr;
  private long post;

  public Wished(long userId, long postId) {
    this.userr=userId;
    this.post=postId;
  }
}
