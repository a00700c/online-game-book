package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setMember("hello", "1234", "it");
        memberRepository.save(member);
    }

    @Test
    public void 검색() {
        Member member = new Member();
        member.setMember("hello", "1234", "it");
        memberRepository.save(member);

        Member findOne = memberRepository.findOneByNickname("it");
        Assertions.assertThat(findOne.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findOne.getPassword()).isEqualTo(member.getPassword());


        Member findTwo = memberRepository.findById("hello").get();
        Assertions.assertThat(findTwo.getPassword()).isEqualTo(member.getPassword());

    }
}
