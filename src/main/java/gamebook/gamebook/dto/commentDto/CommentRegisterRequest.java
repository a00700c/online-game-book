package gamebook.gamebook.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentRegisterRequest {

    private String commentContent;
    private String commentUserId;
    private Long gbNum;
}
