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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class ServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired GamebookRepository gamebookRepository;
    @Autowired GamebookService gamebookService;

    @Test
    public void gamebookMakeTest() {

        Member member = new Member();
        member.initMember("hello", "1234", "it");
        memberRepository.save(member);

        Long findNum = gamebookService.makeNewGamebook("aaa", "bbb", "hello");
        Gamebook findGamebook = gamebookRepository.findById(findNum).get();

        gamebookService.makeNewGamebook("qqq", "www", "hello");
    }
}
