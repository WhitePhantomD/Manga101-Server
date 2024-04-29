package net.htlgkr.zimmeg.pos3.manga101server.services;

import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.repositories.ChapterRepository;
import net.htlgkr.zimmeg.pos3.manga101server.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private PageRepository pageRepository;

    public void deleteAllChapter() {
        chapterRepository.deleteAll();
    }

    public void deleteAllPages() {
        pageRepository.deleteAll();
    }

    public Chapter getChapterById(int id) {
        Chapter chapter = chapterRepository.findById(id).orElse(null);
        chapter.setPages(pageRepository.findPagesByChapterById(id));
        return chapter;
    }

}
