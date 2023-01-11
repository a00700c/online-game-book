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

    public void setMember(Member member) {
        this.member = member;
        member.getLikeys().add(this);
    }

    public void setGamebook(Gamebook gamebook) {
        this.gamebook = gamebook;
        gamebook.getLikeys().add(this);
    }

    public static Likey createLikey(Member member, Gamebook gamebook) {
        Likey likey = new Likey();
        likey.setMember(member);
        likey.setGamebook(gamebook);
        return likey;
    }
}
