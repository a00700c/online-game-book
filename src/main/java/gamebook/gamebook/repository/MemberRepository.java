package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findOneByNickname(String nickname);


}
