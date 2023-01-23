package gamebook.gamebook.service;

import gamebook.gamebook.dto.CommentInfoDto;
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
import java.util.stream.Collectors;

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
    public List<CommentInfoDto> findByGamebook(Long gbNum) {
        List<Comment> comments = commentRepository.findAllByGamebookGbNumOrderByRegDateAsc(gbNum);
        return comments.stream().map(o ->
                        new CommentInfoDto(o.getId(), o.getCommentContent(), o.getRegDate(), o.getMember().getId(), o.getMember().getNickname()))
                .collect(Collectors.toList());
    }


}
