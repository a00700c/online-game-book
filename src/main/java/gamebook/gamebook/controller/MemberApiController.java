package gamebook.gamebook.controller;

import gamebook.gamebook.dto.MemberUpdatePasswordRequest;
import gamebook.gamebook.dto.MemberUpdatePasswordResponse;
import gamebook.gamebook.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {

    private final MemberService memberService;

    @PutMapping("/member/change-password")
    public MemberUpdatePasswordResponse changePassword(@Valid MemberUpdatePasswordRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return null;
        }

        memberService.updatePassword(request);
        return new MemberUpdatePasswordResponse(request.getPassword());
    }
}
