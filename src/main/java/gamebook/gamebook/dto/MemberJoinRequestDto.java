package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberJoinRequestDto {

    private String id;
    private String password;
    private String nickname;

}
