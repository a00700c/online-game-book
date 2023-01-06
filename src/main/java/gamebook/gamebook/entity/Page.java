package gamebook.gamebook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
