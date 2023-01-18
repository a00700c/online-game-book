package gamebook.gamebook.service;

import gamebook.gamebook.entity.Comment;
import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.CommentRepository;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final GamebookRepository gamebookRepository;
    private final CommentRepository commentRepository;

    public Long makeNewComment(String memberId, Long gbNum, String commentContent) {
        Member member = memberRepository.findById(memberId).get();
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        Comment comment = Comment.createComment(member, gamebook, commentContent);
        gamebook.commentUp();
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<Comment> findByGamebook(Long gbNum) {
        return commentRepository.findAllByGamebookGbNumOrderByRegDateAsc(gbNum);
    }


}
