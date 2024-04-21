package net.htlgkr.zimmeg.pos3.manga101server.services;

import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.repositories.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MangaService {
    @Autowired
    private MangaRepository mangaRepository;

    public List<Object[]> getAllMangaNamesAndChapterCount() {
        return mangaRepository.findMangaTitlesAndChapterCount();
    }

    public void deleteAllManga() {
        mangaRepository.deleteAll();
    }

}
