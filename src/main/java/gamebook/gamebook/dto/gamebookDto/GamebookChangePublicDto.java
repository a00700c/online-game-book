package gamebook.gamebook.dto.gamebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GamebookChangePublicDto {

    private Long gbNum;
    private Long isPublic;
}
