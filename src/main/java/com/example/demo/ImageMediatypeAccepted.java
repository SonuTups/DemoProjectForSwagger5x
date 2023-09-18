package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/thisIsSharad")
public class ImageMediatypeAccepted {

    @PostMapping(value = "/uploadImage", consumes = MediaType.IMAGE_PNG_VALUE)
    public String uploadImage(@RequestBody byte[] imageData) {
        try {
            System.out.println("Received data length: " + imageData.length);
            
            return "File uploaded successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}

