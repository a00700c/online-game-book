package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Gamebook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gbNum;

    private String title;
    private String thumbnailPath;
    private Long likeNum;
    private LocalDateTime gbDate;
    private LocalDateTime chDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "gamebook")
    private List<Page> pages = new ArrayList<>();

    @OneToMany(mappedBy = "gamebook")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "gamebook")
    private List<Likey> likeys = new ArrayList<>();

}
