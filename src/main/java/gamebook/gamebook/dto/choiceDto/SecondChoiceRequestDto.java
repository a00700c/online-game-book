package gamebook.gamebook.dto.choiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecondChoiceRequestDto {

    private Long nextS;
    private String secondContent;
    private Long pageId;
}
