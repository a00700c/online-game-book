package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentInfoDto {

    private Long commentId;
    private String commentContent;
    private LocalDateTime regDate;
    private String memberId;
    private String memberNickname;
}
