package gamebook.gamebook.controller;

import gamebook.gamebook.dto.MemberUpdatePasswordDto;
import gamebook.gamebook.dto.MemberUpdatePasswordRequest;
import gamebook.gamebook.dto.MemberUpdatePasswordResponse;
import gamebook.gamebook.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;

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
        memberService.updatePassword(new MemberUpdatePasswordDto(request.getUserId(), request.getPassword()));
        return new MemberUpdatePasswordResponse(request.getPassword(), true, null);
    }

//    @ResponseBody
//    @PatchMapping("/member/change-password")
//    public String checkPassword(HttpServletRequest request) {
//        Enumeration params = request.getParameterNames();
//        while(params.hasMoreElements()) {
//            String name = (String) params.nextElement();
//            System.out.print(name + " : " + request.getParameter(name) + "     ");
//        }
//        System.out.println();
//
//        return "hi";
//    }
}
