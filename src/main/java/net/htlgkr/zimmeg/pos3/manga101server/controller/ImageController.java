package net.htlgkr.zimmeg.pos3.manga101server.controller;

import jakarta.servlet.ServletContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.apache.commons.io.IOUtils;
import org.springframework.web.context.support.ServletContextResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;


@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {

    private ServletContext servletContext;

    @GetMapping("/{rawFilename:.+}")
//    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable String filename) {
//        HttpHeaders headers = new HttpHeaders();
//        InputStream in = servletContext.getResourceAsStream(filename);
//
//
//        if (in == null) {
//            System.out.println("Could not find resource: " + filename);
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        byte[] media = null;
//        try {
//            media = IOUtils.toByteArray(in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
//        return responseEntity;
//    }


//    public Resource getImageAsResource(@PathVariable String filename) {
//        return new ServletContextResource(servletContext, filename);
//    }

//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        this.servletContext = servletContext;
//    }

    public ResponseEntity<Resource> serveFile(@PathVariable String rawFilename) {
        String filename = rawFilename.replace('~','/');
        System.out.println("filename: " + filename);
        Path file = Paths.get(System.getProperty("user.dir"),"/images", filename);
        Resource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().header("Content-Type","image/jpg").body(resource);
    }

}

