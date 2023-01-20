package gamebook.gamebook.controller;

import gamebook.gamebook.dto.*;
import gamebook.gamebook.file.FileStore;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.PageService;
import gamebook.gamebook.web.SessionConst;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GamebookController {

    private final GamebookService gamebookService;
    private final PageService pageService;
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

        Long gbNum = gamebookService.makeNewGamebook(gamebookCreateDto).getGbNum();
        redirectAttributes.addAttribute("gbNum", gbNum);

        return "redirect:/{gbNum}/list";

    }

    @GetMapping("/{gbNum}/list")
    public String showPageList(@PathVariable Long gbNum, Model model) {
        List<PageListDto> findPages = pageService.showAllPages(gbNum);
        model.addAttribute("findPages", findPages);
        return "gamebook/pageList";
    }

    @PostMapping("/{gbNum}/new")
    public String makeNewPage(@PathVariable Long gbNum, RedirectAttributes redirectAttributes) {
        newPageReturnDto pageReturnDto = pageService.makeNewPage(gbNum);
        redirectAttributes.addAttribute("pageId", pageReturnDto.getPageId());
        redirectAttributes.addAttribute("gbNum", gbNum);
        redirectAttributes.addAttribute("pageNum", pageReturnDto.getPageNum());
        return "redirect:/{gbNum}/{pageId}/{pageNum}/new";
    }

    @GetMapping("/{gbNum}/{pageId}/{pageNum}/new")
    public String newPageForm(@PathVariable Long gbNum, @PathVariable Long pageId, @PathVariable Long pageNum, Model model) {
        List<PageListDto> pageList = pageService.showAllPages(gbNum);
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageForm", new newPageForm(pageNum));
        model.addAttribute("pageId", pageId);
        return "gamebook/newPageForm";
    }

    @GetMapping("/{gbNum}/{pageId}/{pageNum}/old")
    public String oldPageForm(@PathVariable Long gbNum, @PathVariable Long pageId, @PathVariable Long pageNum, Model model) {
        PageInfoDto pageInfo = pageService.findPageInfo(new PageIdDto(pageId));
        List<PageListDto> pageList = pageService.showAllPages(gbNum);
        if (pageInfo.getNextF() != null && pageInfo.getNextF() != 0) {
            PageNumDto firstNumDto = pageService.findPageNum(new PageIdDto(pageInfo.getNextF()));
            Long nextFNum = firstNumDto.getPageNum();
            model.addAttribute("nextFNum", nextFNum);
        } else if (pageInfo.getNextF() != null && pageInfo.getNextF() == 0) {
            model.addAttribute("nextFNum", "메인페이지");
        }
        if (pageInfo.getNextS() != null && pageInfo.getNextS() != 0) {
            PageNumDto secondNumDto = pageService.findPageNum(new PageIdDto(pageInfo.getNextS()));
            Long nextSNum = secondNumDto.getPageNum();
            model.addAttribute("nextSNum", nextSNum);
        } else if (pageInfo.getNextS() != null && pageInfo.getNextS() == 0) {
            model.addAttribute("nextSNum", "메인페이지");
        }
        if (pageInfo.getNextT() != null && pageInfo.getNextT() != 0) {
            PageNumDto thirdNumDto = pageService.findPageNum(new PageIdDto(pageInfo.getNextT()));
            Long nextTNum = thirdNumDto.getPageNum();
            model.addAttribute("nextTNum", nextTNum);
        } else if (pageInfo.getNextT() != null && pageInfo.getNextT() == 0) {
            model.addAttribute("nextTNum", "메인페이지");
        }
        model.addAttribute("pageList", pageList);
        model.addAttribute("pageForm", new newPageForm(pageInfo.getContent(), pageInfo.getPicPath(),
                pageNum, pageInfo.getFirstContent(), pageInfo.getSecondContent(), pageInfo.getThirdContent(),
                pageInfo.getNextF(), pageInfo.getNextS(), pageInfo.getNextT()));
        model.addAttribute("pageId", pageId);
        return "gamebook/newPageForm";
    }

}
