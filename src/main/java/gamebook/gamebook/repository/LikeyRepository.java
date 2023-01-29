package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeyRepository extends JpaRepository<Likey, Long> {

    @Query("SELECT distinct l from Likey l join fetch l.gamebook join fetch l.member m where m.id = :id order by l.id desc")
    List<Likey> findAllByMemberIdOrderByIdDesc(@Param("id") String memberId);

    Optional<Likey> findByMemberIdAndGamebookGbNum(String memberId, Long gbNum);

}
