package gamebook.gamebook.dto.pageDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageNumGbNumDto {

    private Long gbNum;
    private Long pageNum;
}
