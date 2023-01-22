package gamebook.gamebook.dto.memberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotEmpty
    private String nickname;

}
