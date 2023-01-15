package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamebookCreateDto {

    private String title;
    private String thumbnailPath;
    private String memberId;
}
