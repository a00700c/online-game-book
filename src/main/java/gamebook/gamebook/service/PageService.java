package gamebook.gamebook.service;

import gamebook.gamebook.entity.Gamebook;
import gamebook.gamebook.entity.Page;
import gamebook.gamebook.repository.GamebookRepository;
import gamebook.gamebook.repository.PageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PageService {

    private final GamebookRepository gamebookRepository;
    private final PageRepository pageRepository;

    public Long makeNewPage(Long gbNum) {

        Gamebook gamebook = gamebookRepository.findById(gbNum).get();
        Page page = Page.createPage(gamebook);
        Long pageNum = (long) gamebook.getPages().size();
        page.setPageNum(pageNum);

        pageRepository.save(page);
        return page.getPageId();
    }

    public void updatePicPath(Long pageId, String picPath) {
        Page page = pageRepository.findById(pageId).get();
        page.setPicPath(picPath);
    }

    public void updateContent(Long pageId, String content) {
        Page page = pageRepository.findById(pageId).get();
        page.setContent(content);
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

    public void deleteFirstChoice(Long pageId) {
        Page page = pageRepository.findById(pageId).get();
        page.deleteFirstChoice();
    }

    public void deleteSecondChoice(Long pageId) {
        Page page = pageRepository.findById(pageId).get();
        page.deleteSecondChoice();
    }

    public void deleteThirdChoice(Long pageId) {
        Page page = pageRepository.findById(pageId).get();
        page.deleteThirdChoice();
    }



}
