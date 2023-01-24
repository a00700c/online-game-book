package gamebook.gamebook.service;

import gamebook.gamebook.dto.LikeMakeDto;
import gamebook.gamebook.dto.LikeNumDto;
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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeyService {

    private final MemberRepository memberRepository;
    private final GamebookRepository gamebookRepository;
    private final LikeyRepository likeyRepository;

    public LikeNumDto makeNewLikey(LikeMakeDto likeMakeDto) {
        Member member = memberRepository.findById(likeMakeDto.getMemberId()).get();
        Gamebook gamebook = gamebookRepository.findById(likeMakeDto.getGbNum()).get();
        Likey likey = Likey.createLikey(member, gamebook);
        likeyRepository.save(likey);
        return new LikeNumDto(gamebook.likeUp());
    }

    public LikeNumDto deleteLikey(LikeMakeDto likeMakeDto) {
        Gamebook gamebook = gamebookRepository.findById(likeMakeDto.getGbNum()).get();
        Likey findLikey = likeyRepository.findByMemberIdAndGamebookGbNum(likeMakeDto.getMemberId(), likeMakeDto.getGbNum()).get();
        likeyRepository.delete(findLikey);
        return new LikeNumDto(gamebook.likeDown());
    }

    @Transactional(readOnly = true)
    public boolean checkIfLike(LikeMakeDto likeMakeDto) {
        Optional<Likey> findLike = likeyRepository.findByMemberIdAndGamebookGbNum(likeMakeDto.getMemberId(), likeMakeDto.getGbNum());
        return findLike.isPresent();
    }

    @Transactional(readOnly = true)
    public List<Gamebook> findUserLike(String memberId) {
        List<Likey> findLikeyList = likeyRepository.findAllByMemberIdOrderByIdDesc(memberId);
        List<Gamebook> gamebookList = new ArrayList<>();
        findLikeyList.forEach(s ->
                gamebookList.add(s.getGamebook()));
        return gamebookList;
    }
}
