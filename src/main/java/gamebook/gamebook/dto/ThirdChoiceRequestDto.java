package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ThirdChoiceRequestDto {

    private Long nextT;
    private String thirdContent;
    private Long pageId;
}
