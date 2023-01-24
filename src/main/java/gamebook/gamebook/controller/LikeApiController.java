package gamebook.gamebook.controller;

import gamebook.gamebook.dto.LikeMakeDto;
import gamebook.gamebook.dto.LikeNumDto;
import gamebook.gamebook.dto.gamebookDto.GamebookGbNumDto;
import gamebook.gamebook.service.LikeyService;
import gamebook.gamebook.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeyService likeyService;

    @PostMapping("/like")
    private LikeNumDto doLike(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, GamebookGbNumDto gamebookGbNumDto) {
        Long gbNum = gamebookGbNumDto.getGbNum();
        return likeyService.makeNewLikey(new LikeMakeDto(loginId, gbNum));
    }

    @DeleteMapping("/like")
    private LikeNumDto doUnlike(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, GamebookGbNumDto gamebookGbNumDto) {
        Long gbNum = gamebookGbNumDto.getGbNum();
        return likeyService.deleteLikey(new LikeMakeDto(loginId, gbNum));
    }
}
