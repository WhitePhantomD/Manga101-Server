package net.htlgkr.zimmeg.pos3.manga101server;

import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
import net.htlgkr.zimmeg.pos3.manga101server.models.Page;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        String filePath = "mangatestres/eleceed_c001 _ c010";

        File mainFolder = new File(filePath);
        int chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;

        System.out.println(chapterCount);

        mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);
        int firstChapter = Integer.parseInt(directories[0].getName().substring(1));

        chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;
        File folder = new File(filePath + "/c" + String.format("%03d", firstChapter) + "/");
        int numberOfPages = folder.listFiles(File::isFile).length;

        System.out.println(folder.getPath());
        System.out.println(numberOfPages);

        filePath = "mangatestres/hero-killer-beolkkul_c001 _ c010";

        mainFolder = new File(filePath);
        chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;

        System.out.println(chapterCount);

        filePath = "mangatestres/solo-leveling_c000 _ c010";

        mainFolder = new File(filePath);
        chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;

        System.out.println(chapterCount);

        mainFolder = new File(filePath);
        directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);
        firstChapter = Integer.parseInt(directories[0].getName().substring(1));

        chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;
        folder = new File(filePath + "/c" + String.format("%03d", firstChapter) + "/");
        numberOfPages = folder.listFiles(File::isFile).length;

        System.out.println(folder.getPath());
        System.out.println(numberOfPages);

        Manga eleceed = new Manga("Eleceed");
        eleceed.setChapters(createChapters2(eleceed, "mangatestres/eleceed_c001 _ c010"));

        Manga heroKillerBeolkkul = new Manga("Hero Killer Beolkkul");
        heroKillerBeolkkul.setChapters(createChapters2(heroKillerBeolkkul, "mangatestres/hero-killer-beolkkul_c001 _ c010"));

        Manga martialPeak = new Manga("Martial Peak");
        martialPeak.setChapters(createChapters2(martialPeak, "mangatestres/martial-peak_c001 _ c010"));

        Manga soloLeveling = new Manga("Solo Leveling");
        soloLeveling.setChapters(createChapters2(soloLeveling, "mangatestres/solo-leveling_c000 _ c010"));

        Manga tongariBooshiNoAtorie = new Manga("Tongari Booshi No Atorie");
        tongariBooshiNoAtorie.setChapters(createChapters2(tongariBooshiNoAtorie, "mangatestres/tongari-booshi-no-atorie_c001 _ c010"));

    }

    private static List<Chapter> createChapters(Manga manga, String filePath) {
        File mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);
        int firstChapter = Integer.parseInt(directories[0].getName().substring(1));

        int chapterCount = (int) mainFolder.listFiles(File::isDirectory).length;
        if (firstChapter == 0) {
            chapterCount = chapterCount - 1;
        }

        return IntStream.rangeClosed(firstChapter, chapterCount)
                .mapToObj(i -> {
                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
                    System.out.println("Chapter" + i + " " + manga.getTitle());
                    File folder = new File(filePath + "/c" + String.format("%03d", i) + "/");
                    System.out.println(folder.getPath());
                    int numberOfPages = folder.listFiles(File::isFile).length;
                    System.out.println("Pages: "+numberOfPages);
                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
                            .peek(page -> System.out.println(page.getPageNumber()))
                            .toList();
                    chapter.setPages(pages);
                    return chapter;
                })
                .collect(Collectors.toList());
    }

    private static List<Chapter> createChapters2(Manga manga, String filePath) {
        File mainFolder = new File(filePath);
        File[] directories = mainFolder.listFiles(File::isDirectory);
        Arrays.sort(directories);

        return Arrays.stream(directories)
                .map(directory -> {
                    String chapterName = directory.getName().substring(1);
                    Chapter chapter = new Chapter("Chapter " + chapterName, Double.parseDouble(chapterName), manga);
                    System.out.println("Chapter" + chapterName + " " + manga.getTitle());
                    File folder = new File(filePath + "/" + directory.getName() + "/");
                    System.out.println(folder.getPath());
                    int numberOfPages = folder.listFiles(File::isFile).length;
                    System.out.println("Pages: "+numberOfPages);
                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
                            .peek(page -> System.out.println(page.getPageNumber()))
                            .collect(Collectors.toList());
                    chapter.setPages(pages);
                    return chapter;
                })
                .toList();
    }
}
