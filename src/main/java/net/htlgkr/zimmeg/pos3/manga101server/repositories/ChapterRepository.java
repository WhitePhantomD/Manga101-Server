package net.htlgkr.zimmeg.pos3.manga101server.repositories;

import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends ListCrudRepository<Chapter, Integer>{

}
