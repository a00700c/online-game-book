package gamebook.gamebook.service;

import gamebook.gamebook.dto.LikeMakeDto;
import gamebook.gamebook.dto.LikeNumDto;
import gamebook.gamebook.dto.gamebookDto.GamebookRankDto;
import gamebook.gamebook.dto.memberDto.MemberIdDto;
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
import java.util.stream.Collectors;

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
    public List<GamebookRankDto> findUserLike(MemberIdDto memberIdDto) {
        List<Likey> findLikeyList = likeyRepository.findAllByMemberIdOrderByIdDesc(memberIdDto.getId());
        return findLikeyList.stream()
                .map(o -> new GamebookRankDto(o.getGamebook().getGbNum(), o.getGamebook().getTitle(),
                        o.getGamebook().getThumbnailPath(), o.getGamebook().getLikeNum(),
                        o.getMember().getNickname(), o.getGamebook().getCommentNum()))
                .collect(Collectors.toList());
    }
}
