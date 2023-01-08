package gamebook.gamebook.service;

import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired GamebookRepository gamebookRepository;
    @Autowired GamebookService gamebookService;

    @Test
    public void memberJoinTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);
    }

    @Test
    public void memberDeleteTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);
        Member member1 = memberService.findOneById("member1");
        assertThat(member1.getNickname()).isEqualTo(member.getNickname());

        memberService.deleteMember("member1");
        assertThrows(IllegalStateException.class, () -> memberService.findOneById("member1"));
    }

    @Test
    public void memberDuplicateTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        Member member2 = new Member();
        member2.initMember("member1", "1234", "no");
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Member member3 = new Member();
        member3.initMember("member2", "1234", "guy");
        assertThrows(IllegalStateException.class, () -> memberService.join(member3));

    }

    @Test
    public void updateNicknameTest() {
        Member member = new Member();
        member.initMember("member1", "1234", "guy");
        memberService.join(member);

        memberService.updateNickname("member1", "change");
        Member findMember = memberService.findOneById("member1");
        assertThat(findMember.getNickname()).isEqualTo("change");
    }



}
