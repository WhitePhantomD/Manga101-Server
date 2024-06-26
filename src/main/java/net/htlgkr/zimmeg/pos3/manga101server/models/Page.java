package net.htlgkr.zimmeg.pos3.manga101server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Page {

    @Id
    @GeneratedValue
    private int id;

    private int pageNumber;

    private String image;

    @ManyToOne
    private Chapter chapter;

    public Page(int pageNumber, String image, Chapter chapter) {
        this.pageNumber = pageNumber;
        this.image = image;
        this.chapter = chapter;
    }
}
