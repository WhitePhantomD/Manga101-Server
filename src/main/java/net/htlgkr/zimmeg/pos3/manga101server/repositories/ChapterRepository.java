package net.htlgkr.zimmeg.pos3.manga101server.repositories;

import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends ListCrudRepository<Chapter, Integer>{

    @Query("SELECT c FROM Chapter c WHERE c.manga.id = ?1")
    List<Chapter> findChapterByMangaById(int mangaId);
}
