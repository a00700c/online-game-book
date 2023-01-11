package gamebook.gamebook.service;

import gamebook.gamebook.entity.Comment;
import gamebook.gamebook.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class CommentServiceTest {

    @Autowired MemberService memberService;
    @Autowired GamebookService gamebookService;
    @Autowired CommentService commentService;

    @Test
    public void commentServiceTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        Member member2 = new Member();
        member2.initMember("member2", "1234", "hi");
        memberService.join(member2);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Long findNum2 = gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        commentService.makeNewComment("member1", findNum, "random comment 1");
        commentService.makeNewComment("member1", findNum, "random comment 2");
        commentService.makeNewComment("member2", findNum, "random comment 3");
        commentService.makeNewComment("member2", findNum2, "random comment 4");
        List<Comment> findList = commentService.findByGamebook(findNum);

        findList.forEach(s ->
                log.info("cId = {}, content = {}, regDate = {}, gbNum = {}, memberId = {}", s.getId(), s.getCommentContent(), s.getRegDate(), s.getGamebook().getGbNum(), s.getMember().getId()));

    }
}
