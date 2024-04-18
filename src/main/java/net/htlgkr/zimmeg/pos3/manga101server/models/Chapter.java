package net.htlgkr.zimmeg.pos3.manga101server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Chapter {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private double chapterNumber;
//    private LocalDate releaseDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Page> pages;

    @ManyToOne
    private Manga manga;

    public Chapter(String title, double chapterNumber, Manga manga) {
        this.title = title;
        this.chapterNumber = chapterNumber;
        this.manga = manga;
    }

}
