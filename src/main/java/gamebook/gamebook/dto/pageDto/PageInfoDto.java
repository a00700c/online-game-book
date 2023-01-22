package gamebook.gamebook.dto.pageDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfoDto {

    private String picPath;
    private String content;
    private String firstContent;
    private String secondContent;
    private String thirdContent;
    private Long nextF;
    private Long nextS;
    private Long nextT;
}
