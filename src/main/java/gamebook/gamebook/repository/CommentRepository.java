package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByGamebookGbNumOrderByRegDateAsc(Long gbNum);
}
