package gamebook.gamebook.dto.gamebookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookSearchDto {

    private String searchName;
    private String searchType;
}
