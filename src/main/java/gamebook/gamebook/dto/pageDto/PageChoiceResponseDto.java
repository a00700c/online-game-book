package gamebook.gamebook.dto.pageDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageChoiceResponseDto {

    private Long next;
    private String content;
}
