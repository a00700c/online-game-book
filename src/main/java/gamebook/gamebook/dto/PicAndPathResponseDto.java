package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PicAndPathResponseDto {

    String filePath;
    String pageContent;
}