package gamebook.gamebook.dto.gamebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookModifyForm {

    private String title;
    private String thumbnailPath;
    private Long isPublic;
}
