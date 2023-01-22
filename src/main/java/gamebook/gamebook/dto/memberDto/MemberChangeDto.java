package gamebook.gamebook.dto.memberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberChangeDto {

    private String userId;
    @NotBlank
    private String password;
    @NotNull
    private String nickname;
}
