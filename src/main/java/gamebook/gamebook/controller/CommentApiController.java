package gamebook.gamebook.controller;

import gamebook.gamebook.dto.commentDto.CommentCreateDto;
import gamebook.gamebook.dto.commentDto.CommentDeleteDto;
import gamebook.gamebook.dto.commentDto.CommentInfoDto;
import gamebook.gamebook.dto.commentDto.CommentRegisterRequest;
import gamebook.gamebook.service.CommentService;
import gamebook.gamebook.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @DeleteMapping("/comment/{gbNum}/{commentId}")
    public String deleteComment(@PathVariable Long gbNum, @PathVariable Long commentId,
                                @SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        commentService.deleteComment(new CommentDeleteDto(commentId, gbNum));
        List<CommentInfoDto> comments = commentService.findByGamebook(gbNum);
        model.addAttribute("commentInfo", comments);
        model.addAttribute("userId", loginId);
        return "fragments/commentFrag";
    }

    @PostMapping("/comment")
    public String registerComment(CommentRegisterRequest request, Model model) {
        commentService.makeNewComment(new CommentCreateDto(request.getCommentUserId(), request.getGbNum(), request.getCommentContent()));
        List<CommentInfoDto> comments = commentService.findByGamebook(request.getGbNum());
        model.addAttribute("commentInfo", comments);
        model.addAttribute("userId", request.getCommentUserId());
        return "fragments/commentFrag";
    }
}
