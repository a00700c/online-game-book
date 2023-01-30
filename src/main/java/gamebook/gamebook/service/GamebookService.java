package gamebook.gamebook.service;

import gamebook.gamebook.dto.gamebookDto.*;
import gamebook.gamebook.dto.memberDto.MemberIdDto;
import gamebook.gamebook.dto.memberDto.MemberNicknameDto;
import gamebook.gamebook.entity.Comment;
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

    public GamebookGbNumDto makeNewGamebook(GamebookCreateDto gamebookCreateDto) {

        Member member = memberRepository.findById(gamebookCreateDto.getMemberId()).get();

        Gamebook gamebook = Gamebook.createGamebook(member);
        gamebook.initGamebook(gamebookCreateDto.getTitle(), gamebookCreateDto.getThumbnailPath());

        gamebookRepository.save(gamebook);
        return new GamebookGbNumDto(gamebook.getGbNum());
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

    public void updateThumbnail(GamebookGbNumPathDto dto) {
        Gamebook gamebook = gamebookRepository.findById(dto.getGbNum()).get();
        gamebook.changeThumbnailPath(dto.getThumbnailPath());
        gamebook.changeDate();
    }

    public void updateTitle(GamebookGbNumTitleDto dto) {
        Gamebook gamebook = gamebookRepository.findById(dto.getGbNum()).get();
        gamebook.changeTitle(dto.getTitle());
        gamebook.changeDate();
    }


    @Transactional(readOnly = true)
    public GamebookMainPageDto findByGbNum(GamebookGbNumDto gamebookGbNumDto) {
        Gamebook gamebook = gamebookRepository.findById(gamebookGbNumDto.getGbNum()).get();
        return new GamebookMainPageDto(gamebook.getGbNum(), gamebook.getTitle(),
                gamebook.getThumbnailPath(), gamebook.getLikeNum(), gamebook.getCommentNum(),
                gamebook.getGbDate(), gamebook.getChDate(), gamebook.getIsPublic(),
                gamebook.getMember().getNickname());
    }

    @Transactional(readOnly = true)
    public List<Gamebook> findAllGamebook() {
        return gamebookRepository.findAllByOrderByGbNumDesc();
    }

    @Transactional(readOnly = true)
    public List<GamebookRankDto> findByTitle(GamebookTitleDto titleDto) {
        List<Gamebook> findGamebooks = gamebookRepository.findAllByTitleOrderByGbNumDesc(titleDto.getTitle());
        if (findGamebooks.isEmpty()) {
            return new ArrayList<>();
        }
        return findGamebooks.stream()
                .map(o -> new GamebookRankDto(o.getGbNum(), o.getTitle(), o.getThumbnailPath(), o.getLikeNum(), o.getMember().getNickname(), o.getCommentNum()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GamebookRankDto> findByNickname(MemberNicknameDto nicknameDto) {
        Optional<Member> findMember = memberRepository.findOneByNickname(nicknameDto.getNickname());
        if (findMember.isEmpty()) {
            return new ArrayList<>();
        }
        String id = findMember.get().getId();
        List<Gamebook> findGamebooks = gamebookRepository.findAllByMemberIdOrderByGbNumDesc(id);
        if (findGamebooks.isEmpty()) {
            return new ArrayList<>();
        }
        return findGamebooks.stream()
                .map(o -> new GamebookRankDto(o.getGbNum(), o.getTitle(), o.getThumbnailPath(), o.getLikeNum(), nicknameDto.getNickname(), o.getCommentNum()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GamebookMyPageDto> findAllByMemberId(MemberIdDto memberIdDto) {
        List<Gamebook> findGamebooks = gamebookRepository.findAllByMemberIdOrderByGbNumDesc(memberIdDto.getId());
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
