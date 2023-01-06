package gamebook.gamebook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

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


}
