package gamebook.gamebook.controller;

import gamebook.gamebook.dto.GamebookMyPageDto;
import gamebook.gamebook.dto.GamebookRankDto;
import gamebook.gamebook.dto.MemberChangeDto;
import gamebook.gamebook.dto.MemberIdDto;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.MemberService;
import gamebook.gamebook.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final GamebookService gamebookService;
    private final MemberService memberService;

    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {

        if (loginId == null) {
            return "home";
        }

        model.addAttribute("loginId", loginId);
        return "loginHome";

    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        List<GamebookRankDto> findGamebooks = gamebookService.rankAllFind();
        model.addAttribute("findGamebooks", findGamebooks);
        return "rankPage";

    }

    @GetMapping("/my-page")
    public String showMyPage() {
        return "myPage/myPage";
    }

    @GetMapping("/my-page/change-user-data")
    public String changeUserForm(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        MemberChangeDto userInfo = memberService.findUserPasswordAndNickname(new MemberIdDto(loginId));
        model.addAttribute("userId", loginId);
        model.addAttribute("userInfo", userInfo);
        return "myPage/changeUserForm";
    }

    @GetMapping("/my-page/my-make-list")
    public String showMyMakeList(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        List<GamebookMyPageDto> findGamebooks = gamebookService.findAllByMemberId(loginId);
        model.addAttribute("findGamebooks", findGamebooks);
        return "myPage/myMakeList";
    }

}
