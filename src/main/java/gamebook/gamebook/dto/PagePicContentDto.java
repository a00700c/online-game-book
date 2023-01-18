package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagePicContentDto {

    private Long pageId;
    private String picPath;
    private String pageContent;
}
