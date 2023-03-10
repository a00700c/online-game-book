package gamebook.gamebook.dto.memberDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdatePasswordRequest {

    private String userId;
    @NotBlank
    private String password;
}
