package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

    List<Page> findAllByGamebookGbNumOrderByPageNumAsc(Long gbNum);

    Optional<Page> findPageByGamebookGbNumAndPageNum(Long gbNum, Long pageNum);
}
