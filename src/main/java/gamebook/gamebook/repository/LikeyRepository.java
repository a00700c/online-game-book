package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeyRepository extends JpaRepository<Likey, Long> {

    List<Likey> findAllByMemberIdOrderByIdDesc(String memberId);

    Optional<Likey> findByMemberIdAndGamebookGbNum(String memberId, Long gbNum);

}
