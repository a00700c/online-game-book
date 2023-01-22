package gamebook.gamebook.dto.memberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateNicknameRequest {

    private String userId;
    @NotEmpty
    private String nickname;
}
