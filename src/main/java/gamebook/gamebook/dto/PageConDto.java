package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageConDto {

    private Long pageId;
    private String content;

}
