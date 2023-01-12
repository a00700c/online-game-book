package gamebook.gamebook.service;

import gamebook.gamebook.dto.MemberJoinRequestDto;
import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class PageServiceTest {

    @Autowired MemberService memberService;
    @Autowired GamebookService gamebookService;
    @Autowired PageService pageService;

    @Test
    public void makeNewPageTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Long findNum2 = gamebookService.makeNewGamebook("gb2", "bbb", "member1");
        Long pageId = pageService.makeNewPage(findNum);
        pageService.makeNewPage(findNum);
        pageService.makeNewPage(findNum);
        pageService.makeNewPage(findNum2);
        pageService.makeNewPage(findNum2);
        pageService.updateContent(pageId, "can you see?");
        pageService.updateFirstChoice(pageId, "go to", 1L);
        pageService.updatePicPath(pageId, "abcd");
        pageService.deleteFirstChoice(pageId);

    }
}
