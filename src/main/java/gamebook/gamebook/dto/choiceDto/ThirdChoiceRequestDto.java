package gamebook.gamebook.dto.choiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThirdChoiceRequestDto {

    private Long nextT;
    private String thirdContent;
    private Long pageId;
}
