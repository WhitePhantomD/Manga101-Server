package net.htlgkr.zimmeg.pos3.manga101server.controller;

import net.htlgkr.zimmeg.pos3.manga101server.dtos.MangaDto;
import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterOnlineService;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterService;
import net.htlgkr.zimmeg.pos3.manga101server.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
