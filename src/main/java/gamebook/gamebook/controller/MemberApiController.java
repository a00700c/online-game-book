package gamebook.gamebook.controller;

import gamebook.gamebook.dto.memberDto.MemberUpdateNicknameRequest;
import gamebook.gamebook.dto.memberDto.MemberUpdateNicknameResponse;
import gamebook.gamebook.dto.memberDto.MemberUpdatePasswordRequest;
import gamebook.gamebook.dto.memberDto.MemberUpdatePasswordResponse;
import gamebook.gamebook.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    @PatchMapping("/member/change-password")
    public MemberUpdatePasswordResponse changePassword(@Valid MemberUpdatePasswordRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new MemberUpdatePasswordResponse(null, false, bindingResult.getFieldError().getDefaultMessage() );
        }
        memberService.updatePassword(request);
        return new MemberUpdatePasswordResponse(request.getPassword(), true, null);
    }

    @PatchMapping("/member/change-nickname")
    public MemberUpdateNicknameResponse changeNickname(@Valid MemberUpdateNicknameRequest request, BindingResult bindingResult) {

        try {
            memberService.validateDuplicateNickname(request.getNickname());
        } catch (IllegalStateException e) {
            bindingResult.addError(new FieldError("memberJoinRequestDto", "nickname", "이미 동일한 닉네임이 존재합니다"));
        }


        if (bindingResult.hasErrors()) {
            return new MemberUpdateNicknameResponse(null, false, bindingResult.getFieldError().getDefaultMessage() );
        }
        memberService.updateNickname(request);
        return new MemberUpdateNicknameResponse(request.getNickname(), true, null);
    }


}
