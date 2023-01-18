package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagePicDto {

    private Long pageId;
    private String picPath;

}
