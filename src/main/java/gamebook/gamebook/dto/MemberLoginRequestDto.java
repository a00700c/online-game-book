package gamebook.gamebook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
}
