package gamebook.gamebook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    private String id;
    private String password;
    private String nickname;


}
