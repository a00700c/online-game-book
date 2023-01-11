package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String commentContent;
    private LocalDateTime regDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gb_num")
    private Gamebook gamebook;

    public void initComment(String commentContent) {
        this.commentContent = commentContent;
        this.regDate = LocalDateTime.now();
    }

    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

    public void setGamebook(Gamebook gamebook) {
        this.gamebook = gamebook;
        gamebook.getComments().add(this);
    }

    public static Comment createComment(Member member, Gamebook gamebook, String commentContent) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setGamebook(gamebook);
        comment.initComment(commentContent);
        return comment;
    }
}



