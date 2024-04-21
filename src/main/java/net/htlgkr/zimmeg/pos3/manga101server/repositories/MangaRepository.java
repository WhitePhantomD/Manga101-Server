package net.htlgkr.zimmeg.pos3.manga101server.repositories;

import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangaRepository extends ListCrudRepository<Manga, Integer>{

    @Query("SELECT m.title, COUNT(c) FROM Manga m JOIN m.chapters c GROUP BY m.title")
    List<Object[]> findMangaTitlesAndChapterCount();
}
