package net.htlgkr.zimmeg.pos3.manga101server.dtos;

import java.util.List;

public record ChapterListDto(int mangaId,
                             String mangaName,
                             List<OnlyChapterDto> chapters) {
}
