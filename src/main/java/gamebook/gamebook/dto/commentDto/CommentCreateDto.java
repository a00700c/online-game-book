package gamebook.gamebook.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreateDto {

    private String memberId;
    private Long gbNum;
    private String commentContent;
}
