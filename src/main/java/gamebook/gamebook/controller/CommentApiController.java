package gamebook.gamebook.controller;

import gamebook.gamebook.dto.commentDto.CommentCreateDto;
import gamebook.gamebook.dto.commentDto.CommentInfoDto;
import gamebook.gamebook.dto.commentDto.CommentRegisterRequest;
import gamebook.gamebook.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @DeleteMapping("/comment/{commentId}")
    public String deleteComment(@PathVariable Long commentId) {
        log.info("{}", commentId);
        return "success";
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
