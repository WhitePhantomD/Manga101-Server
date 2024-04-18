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
import java.util.Arrays;
import java.util.List;
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
            Manga eleceed = new Manga("Eleceed");
            eleceed.setChapters(createChapters(eleceed, "mangatestres/eleceed_c001 _ c010"));
            mangaRepository.save(eleceed);

            Manga heroKillerBeolkkul = new Manga("Hero Killer Beolkkul");
            heroKillerBeolkkul.setChapters(createChapters(heroKillerBeolkkul, "mangatestres/hero-killer-beolkkul_c001 _ c010"));
            mangaRepository.save(heroKillerBeolkkul);

            Manga martialPeak = new Manga("Martial Peak");
            martialPeak.setChapters(createChapters(martialPeak, "mangatestres/martial-peak_c001 _ c010"));
            mangaRepository.save(martialPeak);

            Manga soloLeveling = new Manga("Solo Leveling");
            soloLeveling.setChapters(createChapters(soloLeveling, "mangatestres/solo-leveling_c000 _ c010"));
            mangaRepository.save(soloLeveling);

            Manga tongariBooshiNoAtorie = new Manga("Tongari Booshi No Atorie");
            tongariBooshiNoAtorie.setChapters(createChapters(tongariBooshiNoAtorie, "mangatestres/tongari-booshi-no-atorie_c001 _ c010"));
            mangaRepository.save(tongariBooshiNoAtorie);
        };
    }

    private static List<Chapter> createChapters(Manga manga, String filePath) {
        File mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);

        return Arrays.stream(directories)
                .map(directory -> {
                    String chapterName = directory.getName().substring(1);
                    Chapter chapter = new Chapter("Chapter " + chapterName, Double.parseDouble(chapterName), manga);
                    File folder = new File(filePath + "/" + directory.getName() + "/");
                    int numberOfPages = folder.listFiles(File::isFile).length;
                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
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
                    chapter.setPages(pages);
                    return chapter;
                })
                .toList();
    }

//    private List<Chapter> createChapters(Manga manga, String filePath) {
//        File mainFolder = new File(filePath);
//        File[] directories = mainFolder.listFiles(File::isDirectory);
//        Arrays.sort(directories);
//        int firstChapter = Integer.parseInt(directories[0].getName().substring(1));
//
//        int chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;
//
//        if(firstChapter == 0){
//            chapterCount = chapterCount - 1;
//        }
//
//        return IntStream.rangeClosed(firstChapter, chapterCount)
//                .mapToObj(i -> {
//                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
//                    File folder = new File(filePath + "/c" + String.format("%03d", i) + "/");
//                    int numberOfPages = folder.listFiles(File::isFile).length;
//                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
//                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
//                            .collect(Collectors.toList());
//                    chapter.setPages(pages);
//                    return chapter;
//                })
//                .collect(Collectors.toList());
//    }
//    private List<Chapter> createChapters(Manga manga, String filePath) {
//        File mainFolder = new File(filePath);
//        int chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;
//
//        return IntStream.rangeClosed(1, chapterCount)
//                .mapToObj(i -> {
//                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
//                    File folder = new File(filePath + "/c" + String.format("%03d", i) + "/");
//                    int numberOfPages = folder.listFiles(File::isFile).length;
//                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
//                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
//                            .collect(Collectors.toList());
//                    chapter.setPages(pages);
//                    return chapter;
//                })
//                .collect(Collectors.toList());
//    }

//    private List<Chapter> createChapters(Manga manga, int chapterCount, String filePath) {
//        return IntStream.rangeClosed(1, chapterCount)
//                .mapToObj(i -> {
//                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
//                    File folder = new File(filePath + "/c" + String.format("%03d", i) + "/");
//                    int numberOfPages = folder.listFiles(File::isFile).length;
//                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
//                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
//                            .collect(Collectors.toList());
//                    chapter.setPages(pages);
//                    return chapter;
//                })
//                .collect(Collectors.toList());
//    }
//    private List<Chapter> createChapters(Manga manga, int chapterCount) {
//        return IntStream.rangeClosed(1, chapterCount)
//                .mapToObj(i -> {
//                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
//                    File folder = new File("mangatestres/eleceed_c001 _ c010/c" + String.format("%03d", i) + "/");
//                    int numberOfPages = folder.listFiles(File::isFile).length;
//                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
//                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
//                            .collect(Collectors.toList());
//                    chapter.setPages(pages);
//                    return chapter;
//                })
//                .collect(Collectors.toList());
//
//        //        return IntStream.rangeClosed(1, 10)
////                .mapToObj(i -> {
////                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
////                    List<Page> pages = IntStream.rangeClosed(14, 107)
////                            .mapToObj(j -> new Page(j, new File("mangatestres/eleceed_c001 _ c010/c" + String.format("%03d", i) + "/" + String.format("%03d", j) + ".jpg"), chapter))
////                            .collect(Collectors.toList());
////                    chapter.setPages(pages);
////                    return chapter;
////                })
////                .collect(Collectors.toList());
//    }

}
