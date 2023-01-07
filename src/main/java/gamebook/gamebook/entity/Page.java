package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Page {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageNum;

    private String picPath;
    private String content;
    private Long next;
    private Long nextS;
    private Long nextT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gb_num")
    private Gamebook gamebook;
}
