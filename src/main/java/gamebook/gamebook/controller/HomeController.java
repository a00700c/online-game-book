package gamebook.gamebook.controller;

import gamebook.gamebook.dto.gamebookDto.GamebookMyPageDto;
import gamebook.gamebook.dto.gamebookDto.GamebookRankDto;
import gamebook.gamebook.dto.memberDto.MemberChangeDto;
import gamebook.gamebook.dto.memberDto.MemberIdDto;
import gamebook.gamebook.dto.memberDto.MemberPasswordNicknameDto;
import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.LikeyService;
import gamebook.gamebook.service.MemberService;
import gamebook.gamebook.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final GamebookService gamebookService;
    private final MemberService memberService;
    private final LikeyService likeyService;

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
        MemberPasswordNicknameDto userInfo = memberService.findUserPasswordAndNickname(new MemberIdDto(loginId));
        model.addAttribute("userId", loginId);
        model.addAttribute("password", userInfo.getPassword());
        model.addAttribute("nickname", userInfo.getNickname());
        model.addAttribute("userInfo", new MemberChangeDto());
        return "myPage/changeUserForm";
    }

    @GetMapping("/my-page/delete-user")
    public String deleteUserForm(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        model.addAttribute("userId", loginId);
        return "myPage/deleteUserForm";
    }

    @PostMapping("/my-page/delete-user")
    public String deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String id = (String) session.getAttribute(SessionConst.MEMBER_ID);
            session.invalidate();
            memberService.deleteMember(id);
        }
        return "redirect:/";
    }

    @GetMapping("/my-page/like")
    public String showMyLikeList(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        List<GamebookRankDto> likeList = likeyService.findUserLike(new MemberIdDto(loginId));
        model.addAttribute("likeList", likeList);
        return "myPage/myLikeList";
    }


    @GetMapping("/my-page/my-make-list")
    public String showMyMakeList(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {
        List<GamebookMyPageDto> findGamebooks = gamebookService.findAllByMemberId(new MemberIdDto(loginId));
        model.addAttribute("findGamebooks", findGamebooks);
        return "myPage/myMakeList";
    }

}
