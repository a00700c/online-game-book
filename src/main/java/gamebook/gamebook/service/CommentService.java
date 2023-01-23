package gamebook.gamebook.service;

import gamebook.gamebook.dto.commentDto.CommentCreateDto;
import gamebook.gamebook.dto.commentDto.CommentInfoDto;
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

    public CommentInfoDto makeNewComment(CommentCreateDto commentCreateDto) {
        Member member = memberRepository.findById(commentCreateDto.getMemberId()).get();
        Gamebook gamebook = gamebookRepository.findById(commentCreateDto.getGbNum()).get();
        Comment comment = Comment.createComment(member, gamebook, commentCreateDto.getCommentContent());
        gamebook.commentUp();
        commentRepository.save(comment);
        return new CommentInfoDto(comment.getId(), comment.getCommentContent(), comment.getRegDate(),
                comment.getMember().getId(), comment.getMember().getNickname());
    }

    @Transactional(readOnly = true)
    public List<CommentInfoDto> findByGamebook(Long gbNum) {
        List<Comment> comments = commentRepository.findAllByGamebookGbNumOrderByRegDateAsc(gbNum);
        return comments.stream().map(o ->
                        new CommentInfoDto(o.getId(), o.getCommentContent(), o.getRegDate(), o.getMember().getId(), o.getMember().getNickname()))
                .collect(Collectors.toList());
    }


}
