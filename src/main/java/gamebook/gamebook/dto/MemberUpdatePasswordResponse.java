package gamebook.gamebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdatePasswordResponse {

    private String password;
}
