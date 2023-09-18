package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllMediaTypesAccepted {

    @PostMapping(value = "/uploadFile", consumes = MediaType.ALL_VALUE)
    public String uploadOctetStream(@RequestBody byte[] data) {
        // Process the uploaded binary data
        try {
            // Save the data to a file or perform any other processing
            // For demonstration purposes, let's just print the data length
            System.out.println("Received data length: " + data.length);
            
            return "File uploaded successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
