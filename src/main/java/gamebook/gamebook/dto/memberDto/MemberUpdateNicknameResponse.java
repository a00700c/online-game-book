package gamebook.gamebook.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateNicknameResponse {

    private String nickname;
    private Boolean isSuccess;
    private String errorMessage;
}
