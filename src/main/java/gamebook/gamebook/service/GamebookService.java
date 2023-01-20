package gamebook.gamebook.service;

import gamebook.gamebook.dto.GamebookCreateDto;
import gamebook.gamebook.dto.GamebookMyPageDto;
import gamebook.gamebook.dto.GamebookRankDto;
import gamebook.gamebook.dto.GamebookReturnDto;
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
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GamebookService {

    private final MemberRepository memberRepository;
    private final GamebookRepository gamebookRepository;

    public GamebookReturnDto makeNewGamebook(GamebookCreateDto gamebookCreateDto) {

        Member member = memberRepository.findById(gamebookCreateDto.getMemberId()).get();

        Gamebook gamebook = Gamebook.createGamebook(member);
        gamebook.initGamebook(gamebookCreateDto.getTitle(), gamebookCreateDto.getThumbnailPath());

        gamebookRepository.save(gamebook);
        return new GamebookReturnDto(gamebook.getGbNum());
    }



    public void changeToPublic(Long gbNum) {
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        gamebook.changeToPublic();
    }

    public void changeToPrivate(Long gbNum) {
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        gamebook.changeToPrivate();
    }

    public void updateGamebook(Long gbNum, String title, String thumbnailPath) {
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        gamebook.changeTitle(title);
        gamebook.changeThumbnailPath(thumbnailPath);
        gamebook.changeDate();
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
        if (findMember.isEmpty()) {
            return new ArrayList<>();
        }
        String id = findMember.get().getId();
        return gamebookRepository.findAllByMemberIdOrderByGbNumDesc(id);
    }

    @Transactional(readOnly = true)
    public List<GamebookMyPageDto> findAllByMemberId(String id) {
        List<Gamebook> findGamebooks = gamebookRepository.findAllByMemberIdOrderByGbNumDesc(id);
        return findGamebooks.stream()
                .map(o -> new GamebookMyPageDto(o.getGbNum(), o.getTitle(), o.getThumbnailPath(), o.getLikeNum(), o.getCommentNum(), o.getGbDate(), o.getChDate(), o.getIsPublic()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GamebookRankDto> rankAllFind() {
        List<Gamebook> findGamebooks = gamebookRepository.findAllByOrderByLikeNumDesc();
        return findGamebooks.stream()
                .map(o -> new GamebookRankDto(o.getGbNum(), o.getTitle(), o.getThumbnailPath(), o.getLikeNum(), o.getMember().getNickname(), o.getCommentNum()))
                .collect(Collectors.toList());
    }
}
