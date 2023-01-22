package gamebook.gamebook.service;

import gamebook.gamebook.dto.memberDto.MemberJoinRequestDto;
import gamebook.gamebook.entity.Gamebook;
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
public class GamebookServiceTest {

    @Autowired MemberService memberService;
    @Autowired GamebookService gamebookService;

    @Test
    public void makeNewGamebookTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Gamebook gamebook = gamebookService.findByGbNum(findNum);
        List<Gamebook> findByTitle = gamebookService.findByTitle(gamebook.getTitle());
        findByTitle.forEach(s ->
                log.info("title = {}, thumbnail = {}, likeNum = {}, gbDate = {}, isPublic = {}", s.getTitle(), s.getThumbnailPath(), s.getLikeNum(), s.getGbDate(), s.getIsPublic()));
    }

    @Test
    public void findAllTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        MemberJoinRequestDto memberJoinRequestDto2 = new MemberJoinRequestDto("member2", "1234", "psy");
        memberService.join(memberJoinRequestDto2);

        gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        gamebookService.makeNewGamebook("gb3", "ccc", "member1");
        gamebookService.makeNewGamebook("gb4", "ddd", "member2");

        List<Gamebook> allGamebook = gamebookService.findAllGamebook();
        allGamebook.forEach(s ->
                log.info("title = {}, gbNum = {}", s.getTitle(), s.getGbNum()));
    }

    @Test
    public void findByNicknameTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "ss");
        memberService.join(memberJoinRequestDto);

        MemberJoinRequestDto memberJoinRequestDto2 = new MemberJoinRequestDto("member2", "1234", "psy");
        memberService.join(memberJoinRequestDto2);

        gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        gamebookService.makeNewGamebook("gb3", "ccc", "member1");
        gamebookService.makeNewGamebook("gb4", "ddd", "member2");

        List<Gamebook> allGamebook = gamebookService.findByNickname("ss");
        allGamebook.forEach(s ->
                log.info("title = {}, gbNum = {}", s.getTitle(), s.getGbNum()));
    }

    @Test
    public void functionTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Long findNum2 = gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        Long findNum3 = gamebookService.makeNewGamebook("gb3", "ccc", "member1");

        gamebookService.changeToPublic(findNum);
        gamebookService.updateGamebook(findNum2, "chTitle", "chPath");
        gamebookService.changeToPrivate(findNum);
    }
}
