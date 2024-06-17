package net.htlgkr.zimmeg.pos3.manga101server.dtos;

import jakarta.persistence.Column;
import net.htlgkr.zimmeg.pos3.manga101server.enums.Genres;
import net.htlgkr.zimmeg.pos3.manga101server.enums.State;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MangaDto {
    private String title;
    private String author;
    private String description;
    private List<Genres> genres;
    private State status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}
