package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Likey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeyRepository extends JpaRepository<Likey, Long> {
}
