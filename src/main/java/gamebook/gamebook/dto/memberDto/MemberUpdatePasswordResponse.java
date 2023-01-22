package gamebook.gamebook.dto.memberDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdatePasswordResponse {

    private String password;
    private Boolean isSuccess;
    private String errorMessage;
}
