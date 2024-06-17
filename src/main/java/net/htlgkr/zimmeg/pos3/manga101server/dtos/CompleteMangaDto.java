package net.htlgkr.zimmeg.pos3.manga101server.dtos;

import net.htlgkr.zimmeg.pos3.manga101server.enums.Genres;
import net.htlgkr.zimmeg.pos3.manga101server.enums.State;
import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;

import java.util.List;

public record CompleteMangaDto(int id,
                               String title,
                               String coverImage,
                               String description,
                               String author,
                               State status,
                               List<Genres> genres,
                               int ChapterCount
                               ) {
}