package gamebook.gamebook.repository;

import gamebook.gamebook.dto.MemberJoinRequestDto;
import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.entity.Page;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class PageRepositoryTest {

    @Autowired PageRepository pageRepository;
    @Autowired MemberService memberService;
    @Autowired GamebookService gamebookService;
    /*
    @Test
    @Rollback(value = false)
    public void pageRepositoryTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        MemberJoinRequestDto memberJoinRequestDto2 = new MemberJoinRequestDto("member2", "1234", "auy");
        memberService.join(memberJoinRequestDto2);

        Long findNum = gamebookService.makeNewGamebook("gb1", "aaa", "member1");
        Long findNum2 = gamebookService.makeNewGamebook("gb2", "aaa", "member2");
        Gamebook gamebook = gamebookService.findByGbNum(findNum);
        Gamebook gamebook2 = gamebookService.findByGbNum(findNum2);
        Page page = Page.createPage(gamebook);
        Page page2 = Page.createPage(gamebook);
        Page page3 = Page.createPage(gamebook2);
        pageRepository.save(page);
        pageRepository.save(page2);
        pageRepository.save(page3);

        List<Page> findPages = pageRepository.findAllByGamebookGbNumOrderByPageNumAsc(1L);
        findPages.forEach(s ->
                log.info("page id = {}, gb_num = {}", s.getPageId(), s.getGamebook().getGbNum()));
        page.setFirstChoice("hi, I'm you.", 3L);
        page.deleteFirstChoice();
    }
     */
}
