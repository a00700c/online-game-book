package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Gamebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GamebookRepository extends JpaRepository<Gamebook, Long> {

    @Query("SELECT distinct g from Gamebook g join fetch g.member where g.title = :title order by g.gbNum desc")
    List<Gamebook> findAllByTitleOrderByGbNumDesc(@Param("title") String title);

    List<Gamebook> findAllByMemberIdOrderByGbNumDesc(String id);

    List<Gamebook> findAllByOrderByGbNumDesc();

    @Query("SELECT distinct g from Gamebook g join fetch g.member order by g.likeNum desc")
    List<Gamebook> findAllByOrderByLikeNumDesc();
}
