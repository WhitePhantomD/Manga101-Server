package net.htlgkr.zimmeg.pos3.manga101server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Manga {

    @Id
    @GeneratedValue
    private int id;

    private String title;
//    private String author;
//    private String description;
//    private String coverImage;
//    private String genre;
//    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    public Manga(String title) {
        this.title = title;
    }
}
