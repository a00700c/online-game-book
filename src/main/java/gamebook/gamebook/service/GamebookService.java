package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GamebookService {

    private final MemberRepository memberRepository;
    private final GamebookRepository gamebookRepository;

    public Long makeNewGamebook(String title, String thumbnailPath, String memberId) {

        Member member = memberRepository.findById(memberId).get();

        Gamebook gamebook = Gamebook.createGamebook(member);
        gamebook.initGamebook(title, thumbnailPath);

        gamebookRepository.save(gamebook);
        return gamebook.getGbNum();
    }

    @Transactional(readOnly = true)
    public Gamebook findByGbNum(Long gbNum) {
        return gamebookRepository.findById(gbNum).get();
    }

    @Transactional(readOnly = true)
    public List<Gamebook> findAllGamebook() {
        return gamebookRepository.findAllByOrderByGbNumDesc();
    }

    @Transactional(readOnly = true)
    public List<Gamebook> findByTitle(String title) {
        return gamebookRepository.findAllByTitleOrderByGbNumDesc(title);
    }

    @Transactional(readOnly = true)
    public List<Gamebook> findByNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findOneByNickname(nickname);
        if (!findMember.isPresent()) {
            return new ArrayList<>();
        }
        String id = findMember.get().getId();
        return gamebookRepository.findAllByMemberIdOrderByGbNumDesc(id);
    }
}
