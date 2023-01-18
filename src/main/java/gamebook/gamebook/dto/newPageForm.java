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

    public newPageForm(String content, String picPath, Long pageNum, String firstContent, String secondContent, String thirdContent, Long nextF, Long nextS, Long nextT) {
        this.content = content;
        this.picPath = picPath;
        this.pageNum = pageNum;
        this.firstContent = firstContent;
        this.secondContent = secondContent;
        this.thirdContent = thirdContent;
        this.nextF = nextF;
        this.nextS = nextS;
        this.nextT = nextT;
    }
}
