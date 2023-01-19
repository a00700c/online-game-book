package gamebook.gamebook.controller;

import gamebook.gamebook.dto.*;
import gamebook.gamebook.file.FileStore;
import gamebook.gamebook.service.PageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GamebookApiController {

    @Value("${file.dir}")
    private String fileDir;

    private final PageService pageService;
    private final FileStore fileStore;


    @PostMapping("/page/update-pic")
    public PicResponseDto savePic(PagePicUpdateRequestDto request) throws IOException {
        PageInfoDto pageInfo = pageService.findPageInfo(new PageIdDto(request.getPageId()));
        if (pageInfo.getPicPath() != null) {
            fileStore.deleteFile(pageInfo.getPicPath());
        }
        MultipartFile file = request.getFile();
        String filePath = fileStore.storeFile(file);
        if (filePath == null) {
            return null;
        }
        pageService.updatePicPath(new PagePicDto(request.getPageId(), filePath));
        return new PicResponseDto(filePath);
    }

    @PostMapping("/page/update-con")
    public ConResponseDto saveCon(PageConUpdateRequestDto request) throws IOException {
        pageService.updateContent(new PageConDto(request.getPageId(), request.getContent()));
        return new ConResponseDto(request.getContent());
    }

    @PostMapping("/page/update-first-choice")
    public PageChoiceResponseDto saveFirstChoice(FirstChoiceRequestDto request) {
        if (request.getNextF() == -1) {
            pageService.deleteFirstChoice(new PageIdDto(request.getPageId()));
            return new PageChoiceResponseDto(null, null);
        }
        pageService.updateFirstChoice(request.getPageId(), request.getFirstContent(), request.getNextF());
        if (request.getNextF() == 0) {
            return new PageChoiceResponseDto(0L, request.getFirstContent());
        }
        PageNumDto pageNumDto = pageService.findPageNum(new PageIdDto(request.getNextF()));
        Long pageNum = pageNumDto.getPageNum();
        return new PageChoiceResponseDto(pageNum, request.getFirstContent());
    }

    @PostMapping("/page/update-second-choice")
    public PageChoiceResponseDto saveSecondChoice(SecondChoiceRequestDto request) {
        if (request.getNextS() == -1) {
            pageService.deleteSecondChoice(new PageIdDto(request.getPageId()));
            return new PageChoiceResponseDto(null, null);
        }
        pageService.updateSecondChoice(request.getPageId(), request.getSecondContent(), request.getNextS());
        if (request.getNextS() == 0) {
            return new PageChoiceResponseDto(0L, request.getSecondContent());
        }
        PageNumDto pageNumDto = pageService.findPageNum(new PageIdDto(request.getNextS()));
        Long pageNum = pageNumDto.getPageNum();
        return new PageChoiceResponseDto(pageNum, request.getSecondContent());
    }

    @PostMapping("/page/update-third-choice")
    public PageChoiceResponseDto saveThirdChoice(ThirdChoiceRequestDto request) {
        if (request.getNextT() == -1) {
            pageService.deleteThirdChoice(new PageIdDto(request.getPageId()));
            return new PageChoiceResponseDto(null, null);
        }
        pageService.updateThirdChoice(request.getPageId(), request.getThirdContent(), request.getNextT());
        if (request.getNextT() == 0) {
            return new PageChoiceResponseDto(0L, request.getThirdContent());
        }
        PageNumDto pageNumDto = pageService.findPageNum(new PageIdDto(request.getNextT()));
        Long pageNum = pageNumDto.getPageNum();
        return new PageChoiceResponseDto(pageNum, request.getThirdContent());
    }


    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + filename);
    }
}
