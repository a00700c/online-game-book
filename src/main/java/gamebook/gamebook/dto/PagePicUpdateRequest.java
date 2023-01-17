package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagePicUpdateRequest {

    private MultipartFile file;
    private String content;
    private Long pageId;
}
