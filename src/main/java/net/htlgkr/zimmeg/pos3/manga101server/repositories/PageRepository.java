package net.htlgkr.zimmeg.pos3.manga101server.repositories;

import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends ListCrudRepository<Page, Integer> {

}
