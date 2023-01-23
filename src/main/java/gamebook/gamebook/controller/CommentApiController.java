package gamebook.gamebook.controller;

import gamebook.gamebook.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
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
    public String registerComment(HttpServletRequest request) {
        Enumeration params = request.getParameterNames();
        while(params.hasMoreElements()) {
            String name = (String) params.nextElement();
            System.out.print(name + " : " + request.getParameter(name) + "     ");
        }
        System.out.println();

        return "success";
    }
}
