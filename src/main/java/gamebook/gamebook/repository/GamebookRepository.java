package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Gamebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamebookRepository extends JpaRepository<Gamebook, Long> {

    List<Gamebook> findAllByTitleOrderByGbNumDesc(String title);

    List<Gamebook> findAllByMemberIdOrderByGbNumDesc(String id);

    List<Gamebook> findAllOrderByGbNumDesc();
}
