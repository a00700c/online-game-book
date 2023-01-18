package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class newPageForm {

    private MultipartFile file;
    private String content;
    private String picPath;
    private Long pageNum;
    private String firstContent;
    private String secondContent;
    private String thirdContent;
    private Long nextF;
    private Long nextS;
    private Long nextT;

    public newPageForm(Long pageNum) {
        this.pageNum = pageNum;
    }
}
