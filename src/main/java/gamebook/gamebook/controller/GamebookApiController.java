package gamebook.gamebook.controller;

import gamebook.gamebook.dto.PagePicContentDto;
import gamebook.gamebook.dto.PagePicUpdateRequest;
import gamebook.gamebook.file.FileStore;
import gamebook.gamebook.service.GamebookService;
import gamebook.gamebook.service.PageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GamebookApiController {

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
}
