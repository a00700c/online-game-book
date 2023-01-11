package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
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
public class LikeyServiceTest {

    @Autowired MemberService memberService;
    @Autowired GamebookService gamebookService;
    @Autowired LikeyService likeyService;

    @Test
    public void likeyTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        String memberId = memberService.join(member);

        Member member2 = new Member();
        member2.initMember("member2", "1234", "hi");
        String memberId2 = memberService.join(member2);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Long findNum2 = gamebookService.makeNewGamebook("gb2", "bbb", "member1");

        Long likeCount = likeyService.makeNewLikey(memberId, findNum);
        Long deleteCount = likeyService.deleteLikey(memberId, findNum);
        likeyService.makeNewLikey(memberId2, findNum);
        likeyService.makeNewLikey(memberId, findNum);
        likeyService.makeNewLikey(memberId2, findNum2);
        List<Gamebook> user1Like = likeyService.findUserLike(memberId);
        List<Gamebook> user2Like = likeyService.findUserLike(memberId2);
        user1Like.forEach(s ->
                log.info("user1 = {}, {}, {}, {}, {}, {}", s.getGbNum(), s.getGbDate(), s.getLikeNum(), s.getThumbnailPath(), s.getTitle(), s.getMember().getId()));
        user2Like.forEach(s ->
                log.info("user2 = {}, {}, {}, {}, {}, {}", s.getGbNum(), s.getGbDate(), s.getLikeNum(), s.getThumbnailPath(), s.getTitle(), s.getMember().getId()));
    }
}
