package gamebook.gamebook.service;

import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findById = memberRepository.findById(member.getId());
        Optional<Member> findByNickname = memberRepository.findOneByNickname(member.getNickname());
        if (findById.isPresent()) {
            throw new IllegalStateException("이미 id가 존재합니다.");
        }
        if (findByNickname.isPresent()) {
            throw new IllegalStateException("이미 동일한 닉네임이 존재합니다");
        }
    }

    public void updateNickname(String id, String nickname) {
        Member member = memberRepository.findById(id).get();
        member.changeNickname(nickname);
    }

    public void deleteMember(String id) {
        Member member = memberRepository.findById(id).get();
        memberRepository.delete(member);
    }
}
