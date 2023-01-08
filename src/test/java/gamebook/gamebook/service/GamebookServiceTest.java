package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class GamebookServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired GamebookRepository gamebookRepository;
    @Autowired GamebookService gamebookService;

    @Test
    public void makeNewGamebookTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Gamebook gamebook = gamebookService.findByGbNum(findNum);
        List<Gamebook> findByTitle = gamebookService.findByTitle(gamebook.getTitle());
        findByTitle.stream().forEach(s ->
                log.info("title = {}, thumbnail = {}, likeNum = {}, gbDate = {}, isPublic = {}", s.getTitle(), s.getThumbnailPath(), s.getLikeNum(), s.getGbDate(), s.getIsPublic()));
    }

    @Test
    public void findAllTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        Member member2 = new Member();
        member2.initMember("member2", "1234", "psy");
        memberService.join(member2);

        gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        gamebookService.makeNewGamebook("gb3", "ccc", "member1");
        gamebookService.makeNewGamebook("gb4", "ddd", "member2");

        List<Gamebook> allGamebook = gamebookService.findAllGamebook();
        allGamebook.stream().forEach(s ->
                log.info("title = {}, gbNum = {}", s.getTitle(), s.getGbNum()));
    }

    @Test
    public void findByNicknameTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        Member member2 = new Member();
        member2.initMember("member2", "1234", "psy");
        memberService.join(member2);

        gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        gamebookService.makeNewGamebook("gb3", "ccc", "member1");
        gamebookService.makeNewGamebook("gb4", "ddd", "member2");

        List<Gamebook> allGamebook = gamebookService.findByNickname("ss");
        allGamebook.stream().forEach(s ->
                log.info("title = {}, gbNum = {}", s.getTitle(), s.getGbNum()));
    }
}
