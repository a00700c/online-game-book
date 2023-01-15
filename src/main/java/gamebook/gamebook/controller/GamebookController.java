package gamebook.gamebook.controller;

import gamebook.gamebook.dto.GamebookCreateDto;
import gamebook.gamebook.dto.GamebookForm;
import gamebook.gamebook.file.FileStore;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.web.SessionConst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GamebookController {

    @Value("${file.dir}")
    private String fileDir;

    private final GamebookService gamebookService;
    private final FileStore fileStore;

    @GetMapping("/making/new")
    public String newGamebookForm(Model model) {
        model.addAttribute("gamebookForm", new GamebookForm());
        return "gamebook/createGamebookForm";
    }

    @PostMapping("/making/new")
    public String makeNewGamebook(@SessionAttribute(name = SessionConst.MEMBER_ID, required = false) String loginId,
                                  @Valid GamebookForm form, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            return "gamebook/createGamebookForm";
        }

        MultipartFile file = form.getFile();
        String filePath = fileStore.storeFile(file);
        GamebookCreateDto gamebookCreateDto = new GamebookCreateDto(form.getTitle(), filePath, loginId);

        Long gbNum = gamebookService.makeNewGamebook(gamebookCreateDto);
        redirectAttributes.addAttribute("gbNum", gbNum);

        return "redirect:/gamebook/{gbNum}";

    }
}
