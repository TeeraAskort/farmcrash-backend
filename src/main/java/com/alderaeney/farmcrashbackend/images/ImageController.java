package com.alderaeney.farmcrashbackend.images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    private final String IMAGESPATH = "userImages/";

    @GetMapping("userImages/{fileName}")
    public ResponseEntity<byte[]> downloadFileFromLocal(@PathVariable("fileName") String fileName) throws IOException {
        Path path = Paths.get(IMAGESPATH + fileName);

        byte[] image = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        String extension = FilenameUtils.getExtension(fileName);
        if (extension.equals("gif")) {
            headers.setContentType(MediaType.IMAGE_GIF);
        } else if (extension.equals("jpg") || extension.equals("jpeg")) {
            headers.setContentType(MediaType.IMAGE_JPEG);
        } else {
            headers.setContentType(MediaType.IMAGE_PNG);
        }
        headers.setContentLength(image.length);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
