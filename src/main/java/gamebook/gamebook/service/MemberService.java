package gamebook.gamebook.service;

import gamebook.gamebook.dto.memberDto.*;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public String join(MemberJoinRequestDto memberJoinRequestDto) {
        Member member = new Member();
        member.initMember(memberJoinRequestDto.getId(), memberJoinRequestDto.getPassword(), memberJoinRequestDto.getNickname());
        memberRepository.save(member);
        return memberJoinRequestDto.getId();
    }

    @Transactional(readOnly = true)
    public void validateDuplicateId(String id) {
        Optional<Member> findById = memberRepository.findById(id);
        if (findById.isPresent()) {
            throw new IllegalStateException("이미 id가 존재합니다.");
        }
    }

    @Transactional(readOnly = true)
    public void validateDuplicateNickname(String nickname) {
        Optional<Member> findByNickname = memberRepository.findOneByNickname(nickname);
        if (findByNickname.isPresent()) {
            throw new IllegalStateException("이미 동일한 닉네임이 존재합니다");
        }
    }

    @Transactional(readOnly = true)
    public Member findOneById(String id) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isEmpty()) {
            throw new IllegalStateException("찾으시는 회원이 없습니다.");
        }
        return findMember.get();
    }

    @Transactional(readOnly = true)
    public MemberPasswordNicknameDto findUserPasswordAndNickname(MemberIdDto memberIdDto) {
        String id = memberIdDto.getId();
        Member member = memberRepository.findById(id).get();
        return new MemberPasswordNicknameDto(member.getPassword(), member.getNickname());
    }

    @Transactional(readOnly = true)
    public MemberPasswordDto findPasswordById(String id) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isEmpty()) {
            throw new IllegalStateException("찾으시는 회원이 없습니다.");
        }
        return new MemberPasswordDto(findMember.get().getPassword());
    }


    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public void updatePassword(MemberUpdatePasswordRequest memberDto) {
        Member member = memberRepository.findById(memberDto.getUserId()).get();
        member.changePassword(memberDto.getPassword());
    }

    public void updateNickname(MemberUpdateNicknameRequest memberDto) {
        Member member = memberRepository.findById(memberDto.getUserId()).get();
        member.changeNickname(memberDto.getNickname());
    }

    public void deleteMember(String id) {
        Member member = memberRepository.findById(id).get();
        memberRepository.delete(member);
    }
}
