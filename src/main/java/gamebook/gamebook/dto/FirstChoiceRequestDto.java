package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FirstChoiceRequestDto {

    private Long nextF;
    private String firstContent;
    private Long pageId;
}
