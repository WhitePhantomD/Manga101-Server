package net.htlgkr.zimmeg.pos3.manga101server.controller;

import jakarta.servlet.ServletContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    private ServletContext servletContext;

    @GetMapping("/{rawFilename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String rawFilename) {
        String filename = rawFilename.replace('~', '/');
        System.out.println("filename: " + filename);
        Path file = Paths.get(System.getProperty("user.dir"), "/images", filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().header("Content-Type", "image/jpg").body(resource);
    }

    @GetMapping("/cover/{rawFilename:.+}")
    public ResponseEntity<Resource> serveCoverFile(@PathVariable String rawFilename) {
        try {
            String filename = URLDecoder.decode(rawFilename, StandardCharsets.UTF_8).replace('~', '/');
            System.out.println("filename: " + filename);
            Path file = Paths.get(System.getProperty("user.dir"), "/coverimages", filename+".jpg");
            Resource resource = new UrlResource(file.toUri());

            return ResponseEntity.ok().header("Content-Type", "image/jpg").body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

