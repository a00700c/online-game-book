package gamebook.gamebook.dto.commentDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDeleteDto {

    private Long commentId;
    private Long gbNum;
}
