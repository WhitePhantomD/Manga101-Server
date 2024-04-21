package net.htlgkr.zimmeg.pos3.manga101server;

import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
import net.htlgkr.zimmeg.pos3.manga101server.repositories.MangaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class Manga101ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Manga101ServerApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedDatabase(MangaRepository mangaRepository) {
        return args -> {
            List<Manga> mangas = createMangas("mangatestres");
            mangaRepository.saveAll(mangas);

        };
    }

    private static List<Manga> createMangas(String filePath) {
        File mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);

        Map<String, List<File>> groupedDirectories = Arrays.stream(directories)
                .collect(Collectors.groupingBy(directory -> directory.getName().split("_")[0]));

        List<Manga> mangas = groupedDirectories.entrySet().stream()
                .map(entry -> {
                    String mangaName = entry.getKey();
                    Manga manga = new Manga(mangaName);

                    List<Chapter> chapters = entry.getValue().stream()
                            .flatMap(directory -> createChapters(manga, filePath + "/" + directory.getName() + "/").stream())
                            .collect(Collectors.toList());

                    manga.setChapters(chapters);
                    return manga;
                })
                .collect(Collectors.toList());

        return mangas;
    }

    private static List<Chapter> createChapters(Manga manga, String filePath) {
        File mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);

        return Arrays.stream(directories)
                .map(directory -> {
                    String chapterName = directory.getName().substring(1);
                    Chapter chapter = new Chapter("Chapter " + chapterName, Double.parseDouble(chapterName), manga);
                    chapter.setPages(createPages(chapter,filePath + "/" + directory.getName() + "/"));
                    return chapter;
                })
                .toList();
    }

    private static List<Page> createPages(Chapter chapter, String filePath) {
        File folder = new File(filePath);
        int numberOfPages = folder.listFiles(File::isFile).length;

        return IntStream.rangeClosed(1, numberOfPages)
                .mapToObj(j -> {
                    try {
                        BufferedImage inputImage = ImageIO.read(Files.newInputStream(Path.of(folder.toPath() + "/" + String.format("%03d", j) + ".jpg")));
                        ByteArrayOutputStream jpgContent = new ByteArrayOutputStream();
                        ImageIO.write(inputImage, "jpg", jpgContent);
                        return new Page(j, jpgContent.toByteArray(), chapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .toList();
    }

}
