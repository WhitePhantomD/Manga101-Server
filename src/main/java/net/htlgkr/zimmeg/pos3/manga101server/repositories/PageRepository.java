package net.htlgkr.zimmeg.pos3.manga101server.repositories;

import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends ListCrudRepository<Page, Integer> {

    @Query("SELECT p FROM Page p WHERE p.chapter.id = ?1")
    List<Page> findPagesByChapterById(int chapterId);

}
