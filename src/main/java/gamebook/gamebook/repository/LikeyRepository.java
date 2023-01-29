package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikeyRepository extends JpaRepository<Likey, Long> {

    @Query("SELECT distinct l from Likey l join fetch l.gamebook join fetch l.member")
    List<Likey> findAllByMemberIdOrderByIdDesc(String memberId);

    Optional<Likey> findByMemberIdAndGamebookGbNum(String memberId, Long gbNum);

}
