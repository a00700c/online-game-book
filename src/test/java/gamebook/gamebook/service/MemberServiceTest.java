package gamebook.gamebook.service;

import gamebook.gamebook.dto.MemberJoinRequestDto;
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
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);
    }

    @Test
    public void memberDeleteTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);
        Member member1 = memberService.findOneById("member1");
        assertThat(member1.getNickname()).isEqualTo(memberJoinRequestDto.getNickname());

        memberService.deleteMember("member1");
        assertThrows(IllegalStateException.class, () -> memberService.findOneById("member1"));
    }

    @Test
    public void memberDuplicateTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        MemberJoinRequestDto memberJoinRequestDto2 = new MemberJoinRequestDto("member1", "1234", "no");

        assertThrows(IllegalStateException.class, () -> memberService.join(memberJoinRequestDto2));

        MemberJoinRequestDto memberJoinRequestDto3 = new MemberJoinRequestDto("member2", "1234", "guy");
        assertThrows(IllegalStateException.class, () -> memberService.join(memberJoinRequestDto3));

    }

    @Test
    public void updateNicknameTest() {
        MemberJoinRequestDto memberJoinRequestDto = new MemberJoinRequestDto("member1", "1234", "guy");
        memberService.join(memberJoinRequestDto);

        memberService.updateNickname("member1", "change");
        Member findMember = memberService.findOneById("member1");
        assertThat(findMember.getNickname()).isEqualTo("change");
    }



}
