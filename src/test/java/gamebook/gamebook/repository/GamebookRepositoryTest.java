package gamebook.gamebook.repository;

import gamebook.gamebook.entity.Gamebook;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@Slf4j
public class GamebookRepositoryTest {

    @Autowired
    GamebookRepository gamebookRepository;

    @Test
    public void 생성() {
        Gamebook gamebook = new Gamebook();
        gamebook.initGamebook("baby", "gogo");

        gamebookRepository.save(gamebook);
        List<Gamebook> findList = gamebookRepository.findAllByTitle("baby");
        findList.stream().forEach(s ->
                log.info("title = {}, thumb = {}, date = {}, like = {}",s.getTitle(), s.getThumbnailPath(), s.getGbDate(), s.getLikeNum())
        );

    }
}
