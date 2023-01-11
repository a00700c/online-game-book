package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeyRepository extends JpaRepository<Likey, Long> {

    List<Likey> findAllByMemberIdOrderByIdDesc(Long memberId);
}
