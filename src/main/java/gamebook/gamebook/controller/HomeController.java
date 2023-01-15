package gamebook.gamebook.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/loginHome")
    public String loginHome() {
        return "loginHome";
    }
}
