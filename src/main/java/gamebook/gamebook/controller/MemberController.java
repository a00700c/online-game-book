package gamebook.gamebook.controller;

import gamebook.gamebook.dto.MemberJoinRequestDto;
import gamebook.gamebook.dto.MemberLoginRequestDto;
import gamebook.gamebook.entity.Member;
import gamebook.gamebook.service.MemberService;
import gamebook.gamebook.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model, MemberJoinRequestDto memberJoinRequestDto) {
        model.addAttribute("memberJoinRequestDto", new MemberJoinRequestDto());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(@Valid MemberJoinRequestDto memberJoinRequestDto, BindingResult bindingResult) {

        try {
            memberService.validateDuplicateId(memberJoinRequestDto.getId());
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberJoinRequestDto", "id", "이미 동일한 id가 존재합니다"));
        }

        try {
            memberService.validateDuplicateNickname(memberJoinRequestDto.getNickname());
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberJoinRequestDto", "nickname", "이미 동일한 닉네임이 존재합니다"));
        }

        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.join(memberJoinRequestDto);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("memberLoginRequestDto", new MemberJoinRequestDto());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid MemberLoginRequestDto memberLoginRequestDto, BindingResult bindingResult, HttpServletRequest request) {

        try {
            String findPassword = memberService.findPasswordById(memberLoginRequestDto.getId()).getPassword();
            if (!memberLoginRequestDto.getPassword().equals(findPassword)) {
                bindingResult.addError(new FieldError("memberLoginRequestDto", "password", "id와 패스워드가 일치하지 않습니다"));
            }
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberLoginRequestDto", "id", "ID가 존재하지 않습니다"));
        }

        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID, memberLoginRequestDto.getId());

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
