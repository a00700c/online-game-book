package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookMyPageDto {

    private Long gbNum;
    private String title;
    private String thumbnailPath;
    private Long likeNum;
    private Long commentNum;
    private LocalDateTime gbDate;
    private LocalDateTime chDate;
    private Long isPublic;

}
