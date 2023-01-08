package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findOneByNickname(String nickname);


}
