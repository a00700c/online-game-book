package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gamebook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gbNum;

    private String title;
    private String thumbnailPath;
    private Long likeNum;
    private LocalDateTime gbDate;
    private LocalDateTime chDate;
    private Long isPublic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "gamebook")
    private List<Page> pages = new ArrayList<>();

    @OneToMany(mappedBy = "gamebook")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "gamebook")
    private List<Likey> likeys = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
        member.getGamebooks().add(this);
    }

    public static Gamebook createGamebook(Member member) {
        Gamebook gamebook = new Gamebook();
        gamebook.setMember(member);
        return gamebook;
    }

    public void initGamebook(String title, String thumbnailPath) {
        this.title = title;
        this.thumbnailPath = thumbnailPath;
        this.likeNum = 0L;
        this.gbDate = LocalDateTime.now();
        this.isPublic = 0L;
    }
}
