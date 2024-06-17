package net.htlgkr.zimmeg.pos3.manga101server.controller;


import net.htlgkr.zimmeg.pos3.manga101server.dtos.*;
import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import net.htlgkr.zimmeg.pos3.manga101server.repositories.MangaRepository;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterOnlineService;
import net.htlgkr.zimmeg.pos3.manga101server.services.ChapterService;
import net.htlgkr.zimmeg.pos3.manga101server.services.MangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    @Autowired
    private MangaRepository mangaRepository;

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
                        .toList(),
                chapter.getManga().getId(),
                chapterService.getNextChapterId(chapter.getManga().getId(),chapter.getChapterNumber()),
                chapterService.getPreviousChapterId(chapter.getManga().getId(),chapter.getChapterNumber()));
    }

    @GetMapping("/chapter/list/{id}")
    public ChapterListDto getChapterList(@PathVariable int id) {
        Manga manga = mangaService.getMangaById(id);
        return new ChapterListDto(manga.getId(),manga.getTitle(),manga.getChapters().stream()
                .map(chapter -> new OnlyChapterDto(chapter.getId(),chapter.getTitle(),chapter.getChapterNumber()))
                .toList());
    }

    @GetMapping("/chapter/next/{id}")
    public int getNextChapterId(@PathVariable int id) {
        Chapter chapter = chapterService.getChapterById(id);
        return chapterService.getNextChapterId(chapter.getManga().getId(),chapter.getChapterNumber());
    }

    @GetMapping("/chapter/previous/{id}")
    public int getPreviousChapterId(@PathVariable int id) {
        Chapter chapter = chapterService.getChapterById(id);
        return chapterService.getPreviousChapterId(chapter.getManga().getId(),chapter.getChapterNumber());
    }

    @PutMapping("/manga/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable int id, @RequestBody MangaDto mangaDto) throws IOException {
        Manga manga = mangaRepository.findById(id).orElseThrow(() -> new RuntimeException("Manga not found"));
        manga.setTitle(mangaDto.getTitle());
        manga.setAuthor(mangaDto.getAuthor());
        manga.setDescription(mangaDto.getDescription());
        manga.setCoverImage("coverimages/"+mangaDto.getTitle());
        manga.setGenres(mangaDto.getGenres());
        manga.setStatus(mangaDto.getStatus());

        Manga updatedManga = mangaRepository.save(manga);
        return ResponseEntity.ok(updatedManga);
    }

    @GetMapping("/manga/{id}")
    public CompleteMangaDto getMangaById(@PathVariable int id) {
        Manga manga = mangaService.getCompleteMangaById(id);
        return new CompleteMangaDto(
                manga.getId(),
                manga.getTitle(),
                manga.getCoverImage(),
                manga.getDescription(),
                manga.getAuthor(),
                manga.getStatus(),
                manga.getGenres(),
                manga.getChapters().size()
                );
    }

    @GetMapping("/search/{subString}")
    public List<SearchDto> searchManga(@PathVariable String subString) {

        return mangaService.getMangaByString(subString).stream()
                .map(manga -> new SearchDto(manga.getId(),manga.getTitle(),manga.getAuthor(),manga.getCoverImage(),manga.getGenres().toString(),manga.getStatus().toString(),String.valueOf(manga.getChapters().size())))
                .toList();
    }

    @GetMapping("/manga/all")
    public List<CompleteMangaDto> getMangaAll() {
        return mangaRepository.findAll().stream().map(manga -> new CompleteMangaDto(
                manga.getId(),
                manga.getTitle(),
                manga.getCoverImage(),
                manga.getDescription(),
                manga.getAuthor(),
                manga.getStatus(),
                manga.getGenres(),
                manga.getChapters().size()
        )).toList();
    }

}
