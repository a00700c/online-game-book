package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
