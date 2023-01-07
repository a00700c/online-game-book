package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Gamebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamebookRepository extends JpaRepository<Gamebook, Long> {
}
