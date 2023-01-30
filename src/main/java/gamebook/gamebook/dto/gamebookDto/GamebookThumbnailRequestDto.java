package gamebook.gamebook.dto.gamebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookThumbnailRequestDto {

    private MultipartFile file;
    private Long gbNum;
}
