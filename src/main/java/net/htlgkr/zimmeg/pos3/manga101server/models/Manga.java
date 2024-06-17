package net.htlgkr.zimmeg.pos3.manga101server.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.htlgkr.zimmeg.pos3.manga101server.enums.Genres;
import net.htlgkr.zimmeg.pos3.manga101server.enums.State;

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
    private String author;

    @Column(length = 1000)
    private String description;

    private String coverImage;

    @ElementCollection(targetClass = Genres.class)
    @Enumerated(EnumType.STRING)
    private List<Genres> genres;

    @Enumerated(EnumType.STRING)
    private State status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    public Manga(String title) {
        this.title = title;
    }

}
