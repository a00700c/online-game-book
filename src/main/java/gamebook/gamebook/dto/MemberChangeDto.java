package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberChangeDto {

    private String password;
    private String nickname;
}
