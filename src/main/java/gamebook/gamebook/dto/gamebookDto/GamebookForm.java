package gamebook.gamebook.dto.gamebookDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class GamebookForm {

    @NotNull
    private String title;
    private MultipartFile file;
}
