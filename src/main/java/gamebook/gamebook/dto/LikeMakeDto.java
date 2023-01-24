package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LikeMakeDto {

    private String memberId;
    private Long gbNum;
}
