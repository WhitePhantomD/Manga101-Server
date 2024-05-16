package net.htlgkr.zimmeg.pos3.manga101server.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import net.htlgkr.zimmeg.pos3.manga101server.dtos.ChapterDto;
import net.htlgkr.zimmeg.pos3.manga101server.dtos.ChapterDtoTest;
import net.htlgkr.zimmeg.pos3.manga101server.dtos.MangaDto;
import net.htlgkr.zimmeg.pos3.manga101server.dtos.PageDto;
import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterOnlineService;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterService;
import net.htlgkr.zimmeg.pos3.manga101server.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/manga")
public class MangaController {
    @Autowired
    private MangaService mangaService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterOnlineService chapterOnlineService;

    @GetMapping("/all")
    public List<Object[]> getAllManga() {
       return mangaService.getAllMangaNamesAndChapterCount();
    }

    @DeleteMapping("/all")
    public void deleteManga() {
        mangaService.deleteAllManga(); //deletes all mangas and all chapters as well the pages
    }

    @GetMapping("/test/chapter/{id}")
    public ChapterDtoTest getChapterByIdTest(@PathVariable int id) {
        Chapter chapter = chapterService.getChapterById(id);
        return new ChapterDtoTest(chapter.getId(), chapter.getTitle(), chapter.getChapterNumber(),chapter.getPages().stream().mapToInt(Page::getPageNumber).toArray());
    }

    @GetMapping("/chapter/{id}")
    public ChapterDto getChapterById(@PathVariable int id) {
        Chapter chapter = chapterService.getChapterById(id);
        return new ChapterDto(chapter.getId(),
                chapter.getTitle(),
                chapter.getChapterNumber(),
                chapter.getPages().stream()
                        .map(page -> new PageDto(page.getPageNumber(),page.getImage().replace('/','~').replaceFirst("~","/"),chapter.getId()))
                        .toList(),chapter.getManga().getId());
    }

}
