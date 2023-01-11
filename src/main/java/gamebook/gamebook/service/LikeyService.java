package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Likey;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.LikeyRepository;
import gamebook.gamebook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeyService {

    private final MemberRepository memberRepository;
    private final GamebookRepository gamebookRepository;
    private final LikeyRepository likeyRepository;

    public Long makeNewLikey(String memberId, Long gbNum) {
        Member member = memberRepository.findById(memberId).get();
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        Likey likey = Likey.createLikey(member, gamebook);
        likeyRepository.save(likey);
        return gamebook.likeUp();
    }

    public Long deleteLikey(String memberId, Long gbNum) {
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        Likey findLikey = likeyRepository.findByMemberIdAndGamebookGbNum(memberId, gbNum);
        likeyRepository.delete(findLikey);
        return gamebook.likeDown();
    }

    public List<Gamebook> findUserLike(String memberId) {
        List<Likey> findLikeyList = likeyRepository.findAllByMemberIdOrderByIdDesc(memberId);
        List<Gamebook> gamebookList = new ArrayList<>();
        findLikeyList.forEach(s ->
                gamebookList.add(s.getGamebook()));
        return gamebookList;
    }
}
