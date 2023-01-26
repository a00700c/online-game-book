package gamebook.gamebook.dto.likeDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeMakeDto {

    private String memberId;
    private Long gbNum;
}
