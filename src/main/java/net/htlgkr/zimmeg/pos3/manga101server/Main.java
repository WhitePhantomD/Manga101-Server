//package net.htlgkr.zimmeg.pos3.manga101server;
//
//import net.htlgkr.zimmeg.pos3.manga101server.models.Chapter;
//import net.htlgkr.zimmeg.pos3.manga101server.models.Manga;
//import net.htlgkr.zimmeg.pos3.manga101server.models.Page;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class Main {
//    public static void main(String[] args) {
//        createMangas("mangatestres");
//    }
//
//    private static List<Manga> createMangas(String filePath) {
//        File mainFolder = new File(filePath);
//        System.out.println(mainFolder.getPath());
//        File[] directories = mainFolder.listFiles(File::isDirectory);
//
//        Map<String, List<File>> groupedDirectories = Arrays.stream(directories)
//                .collect(Collectors.groupingBy(directory -> directory.getName().split("_")[0]));
//
//        List<Manga> mangas = groupedDirectories.entrySet().stream()
//                .map(entry -> {
//                    String mangaName = entry.getKey();
//                    Manga manga = new Manga(mangaName);
//                    System.out.println("Manga: " + mangaName);
//
//                    List<Chapter> chapters = entry.getValue().stream()
//                            .flatMap(directory -> createChapters(manga, filePath + "/" + directory.getName() + "/").stream())
//                            .collect(Collectors.toList());
//
//                    manga.setChapters(chapters);
//                    return manga;
//                })
//                .collect(Collectors.toList());
//
//        return mangas;
//    }
//
////    private static List<Manga> createMangas(String filePath) {
////        List<Manga> mangas = new ArrayList<>();
////
////        File mainFolder = new File(filePath);
////        System.out.println(mainFolder.getPath());
////        File[] directories = mainFolder.listFiles(File::isDirectory);
////        Arrays.stream(directories)
////                .forEach(directory -> {
////                    String directoryName = directory.getName();
////                    String mangaName = directoryName.split("_")[0];
////
////                    Manga manga = new Manga(mangaName);
////                    System.out.println("Manga: " + directory.getName());
////                    manga.setChapters(createChapters(manga, filePath + "/" + directory.getName() + "/"));
////                    mangas.add(manga);
////                });
////
////        return null;
////    }
//
//    private static List<Chapter> createChapters(Manga manga, String filePath) {
//        File mainFolder = new File(filePath);
//        File[] directories = mainFolder.listFiles(File::isDirectory);
//        Arrays.sort(directories);
//
//        return Arrays.stream(directories)
//                .map(directory -> {
//                    String chapterName = directory.getName().substring(1);
//                    Chapter chapter = new Chapter("Chapter " + chapterName, Double.parseDouble(chapterName), manga);
//                    System.out.println(chapterName);
//                    chapter.setPages(createPages(chapter,filePath + "/" + directory.getName() + "/"));
//                    return chapter;
//                })
//                .toList();
//    }
//
//    private static List<Page> createPages(Chapter chapter, String filePath) {
//        File folder = new File(filePath);
//        int numberOfPages = folder.listFiles(File::isFile).length;
//
//        return IntStream.rangeClosed(1, numberOfPages)
//                .mapToObj(j -> {
//                    try {
//                        BufferedImage inputImage = ImageIO.read(Files.newInputStream(Path.of(folder.toPath() + "/" + String.format("%03d", j) + ".jpg")));
//                        //System.out.println("Page: " + j);
//                        ByteArrayOutputStream jpgContent = new ByteArrayOutputStream();
//                        ImageIO.write(inputImage, "jpg", jpgContent);
//                        return new Page(j, jpgContent.toByteArray(), chapter);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                })
//                .toList();
//    }
//
////    private static List<Chapter> createChapters(Manga manga, String filePath) {
////        File mainFolder = new File(filePath);
////        File[] directories = mainFolder.listFiles(File::isDirectory);
////        Arrays.sort(directories);
////        int firstChapter = Integer.parseInt(directories[0].getName().substring(1));
////
////        int chapterCount = mainFolder.listFiles(File::isDirectory).length;
////        if (firstChapter == 0) {
////            chapterCount = chapterCount - 1;
////        }
////
////        return IntStream.rangeClosed(firstChapter, chapterCount)
////                .mapToObj(i -> {
////                    Chapter chapter = new Chapter("Chapter " + i, i, manga);
////                    System.out.println("Chapter" + i + " " + manga.getTitle());
////                    File folder = new File(filePath + "/c" + String.format("%03d", i) + "/");
////                    System.out.println(folder.getPath());
////                    int numberOfPages = folder.listFiles(File::isFile).length;
////                    System.out.println("Pages: "+numberOfPages);
////                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
////                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
////                            .peek(page -> System.out.println(page.getPageNumber()))
////                            .toList();
////                    chapter.setPages(pages);
////                    return chapter;
////                })
////                .collect(Collectors.toList());
////    }
////
////    private static List<Chapter> createChapters2(Manga manga, String filePath) {
////        File mainFolder = new File(filePath);
////        File[] directories = mainFolder.listFiles(File::isDirectory);
////        Arrays.sort(directories);
////
////        return Arrays.stream(directories)
////                .map(directory -> {
////                    String chapterName = directory.getName().substring(1);
////                    Chapter chapter = new Chapter("Chapter " + chapterName, Double.parseDouble(chapterName), manga);
////                    System.out.println("Chapter" + chapterName + " " + manga.getTitle());
////                    File folder = new File(filePath + "/" + directory.getName() + "/");
////                    System.out.println(folder.getPath());
////                    int numberOfPages = folder.listFiles(File::isFile).length;
////                    System.out.println("Pages: "+numberOfPages);
////                    List<Page> pages = IntStream.rangeClosed(1, numberOfPages)
////                            .mapToObj(j -> new Page(j, new File(folder.getPath() + "/" + String.format("%03d", j) + ".jpg"), chapter))
////                            .peek(page -> System.out.println(page.getPageNumber()))
////                            .collect(Collectors.toList());
////                    chapter.setPages(pages);
////                    return chapter;
////                })
////                .toList();
////    }
//}
