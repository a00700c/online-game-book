package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Likey {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likey_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gb_num")
    private Gamebook gamebook;
}
