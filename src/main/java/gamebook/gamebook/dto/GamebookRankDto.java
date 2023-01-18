package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookRankDto {

    private Long gbNum;
    private String title;
    private String thumbnailPath;
    private Long likeNum;
    private String nickname;
    private Long commentCount;
}
