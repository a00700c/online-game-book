package gamebook.gamebook.dto.gamebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookMainPageDto {

    private Long gbNum;
    private String title;
    private String thumbnailPath;
    private Long likeNum;
    private Long commentNum;
    private LocalDateTime gbDate;
    private LocalDateTime chDate;
    private Long isPublic;
    private String makerNickname;

}
