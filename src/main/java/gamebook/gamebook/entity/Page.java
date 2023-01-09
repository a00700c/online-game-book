package gamebook.gamebook.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Page {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;

    private String picPath;
    private String content;
    private Long pageNum;
    private String firstContent;
    private String secondContent;
    private String thirdContent;
    private Long nextF;
    private Long nextS;
    private Long nextT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gb_num")
    private Gamebook gamebook;

    public void setGamebook(Gamebook gamebook) {
        this.gamebook = gamebook;
        gamebook.getPages().add(this);
    }

    public static Page createPage(Gamebook gamebook) {
        Page page = new Page();
        page.setGamebook(gamebook);
        return page;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public void plusFirstChoice(String firstContent, Long nextF) {
        this.firstContent = firstContent;
        this.nextF = nextF;
    }

    public void deleteFirstChoice() {
        this.firstContent = null;
        this.nextF = null;
    }

    public void plusSecondChoice(String secondContent, Long nextS) {
        this.secondContent = secondContent;
        this.nextS = nextS;
    }

    public void deleteSecondChoice() {
        this.secondContent = null;
        this.nextS = null;
    }

    public void plusThirdChoice(String thirdContent, Long nextT) {
        this.thirdContent = thirdContent;
        this.nextT = nextT;
    }

    public void deleteThirdChoice() {
        this.thirdContent = null;
        this.nextT = null;
    }

}
