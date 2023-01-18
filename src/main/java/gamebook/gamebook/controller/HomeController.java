package gamebook.gamebook.controller;

import gamebook.gamebook.dto.GamebookRankDto;
import gamebook.gamebook.service.GamebookService;
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
}
