package gamebook.gamebook.controller;

import gamebook.gamebook.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId, Model model) {

        if (loginId == null) {
            return "home";
        }

        model.addAttribute("loginId", loginId);
        return "loginHome";

    }
}
