package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {
}
