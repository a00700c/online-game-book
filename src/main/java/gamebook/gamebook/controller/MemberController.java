package gamebook.gamebook.controller;

import gamebook.gamebook.dto.MemberJoinRequestDto;
import gamebook.gamebook.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
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
}
