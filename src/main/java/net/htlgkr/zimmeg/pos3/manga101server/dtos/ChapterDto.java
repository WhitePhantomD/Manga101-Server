package net.htlgkr.zimmeg.pos3.manga101server.dtos;

import java.util.List;

public record ChapterDto(int id,
                         String title,
                         double chapterNumber,
                         List<PageDto> images,
                         int mangaId) {
}
