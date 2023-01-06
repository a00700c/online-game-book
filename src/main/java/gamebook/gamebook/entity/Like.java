package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

}
