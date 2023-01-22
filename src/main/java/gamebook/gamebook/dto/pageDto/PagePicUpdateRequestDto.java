package gamebook.gamebook.dto.pageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagePicUpdateRequestDto {

    private MultipartFile file;
    private Long pageId;

}
