package gamebook.gamebook.controller;

import gamebook.gamebook.dto.PagePicContentDto;
import gamebook.gamebook.dto.PagePicUpdateRequest;
import gamebook.gamebook.file.FileStore;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GamebookApiController {

    @Value("${file.dir}")
    private String fileDir;

    private final GamebookService gamebookService;
    private final PageService pageService;
    private final FileStore fileStore;

    @PostMapping("/page/update-pic-con")
    public String savePicAndContent(PagePicUpdateRequest request) throws IOException {
        MultipartFile file = request.getFile();
        String filePath = fileStore.storeFile(file);
        pageService.updatePicPathAndContent(new PagePicContentDto(request.getPageId(), filePath, request.getContent()));

        return "ok";
    }

    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileDir + filename);
    }
}
