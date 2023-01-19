package gamebook.gamebook.service;

import gamebook.gamebook.dto.*;
import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Page;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PageService {

    private final GamebookRepository gamebookRepository;
    private final PageRepository pageRepository;

    public newPageReturnDto makeNewPage(Long gbNum) {
        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        Page page = Page.createPage(gamebook);
        Long pageNum = (long) gamebook.getPages().size();
        page.setPageNum(pageNum);

        pageRepository.save(page);
        return new newPageReturnDto(page.getPageId(), pageNum);
    }

    @Transactional(readOnly = true)
    public PageInfoDto findPageInfo(PageIdDto pageIdDto) {
        Long pageId = pageIdDto.getPageId();
        Page page = pageRepository.findById(pageId).get();
        return new PageInfoDto(page.getPicPath(), page.getContent(),
                page.getFirstContent(), page.getSecondContent(), page.getThirdContent(),
                page.getNextF(), page.getNextS(), page.getNextT());
    }

    @Transactional(readOnly = true)
    public PageNumDto findPageNum(PageIdDto pageIdDto) {
        Long pageId = pageIdDto.getPageId();
        Page page = pageRepository.findById(pageId).get();
        return new PageNumDto(page.getPageNum());
    }

    @Transactional(readOnly = true)
    public List<PageListDto> showAllPages(Long gbNum) {
        List<Page> findPages = pageRepository.findAllByGamebookGbNumOrderByPageNumAsc(gbNum);
        return findPages.stream()
                .map(o -> new PageListDto(o.getPicPath(), o.getPageNum(), o.getPageId()))
                .collect(Collectors.toList());
    }


    public void updatePicPath(PagePicDto pagePicDto) {
        Page page = pageRepository.findById(pagePicDto.getPageId()).get();
        page.setPicPath(pagePicDto.getPicPath());
    }

    public void updateContent(PageConDto pageConDto) {
        Page page = pageRepository.findById(pageConDto.getPageId()).get();
        page.setContent(pageConDto.getContent());
    }

    public void updateFirstChoice(Long pageId, String firstContent, Long nextF) {
        Page page = pageRepository.findById(pageId).get();
        page.setFirstChoice(firstContent, nextF);
    }

    public void updateSecondChoice(Long pageId, String secondContent, Long nextS) {
        Page page = pageRepository.findById(pageId).get();
        page.setSecondChoice(secondContent, nextS);
    }

    public void updateThirdChoice(Long pageId, String thirdContent, Long nextT) {
        Page page = pageRepository.findById(pageId).get();
        page.setThirdChoice(thirdContent, nextT);
    }

    public void deleteFirstChoice(PageIdDto pageIdDto) {
        Long pageId = pageIdDto.getPageId();
        Page page = pageRepository.findById(pageId).get();
        page.deleteFirstChoice();
    }

    public void deleteSecondChoice(PageIdDto pageIdDto) {
        Long pageId = pageIdDto.getPageId();
        Page page = pageRepository.findById(pageId).get();
        page.deleteSecondChoice();
    }

    public void deleteThirdChoice(PageIdDto pageIdDto) {
        Long pageId = pageIdDto.getPageId();
        Page page = pageRepository.findById(pageId).get();
        page.deleteThirdChoice();
    }



}
