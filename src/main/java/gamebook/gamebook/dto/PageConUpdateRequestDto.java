package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageConUpdateRequestDto {

    private String content;
    private Long pageId;
}
