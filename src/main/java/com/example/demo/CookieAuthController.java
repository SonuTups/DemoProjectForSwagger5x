package com.example.demo;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class CookieAuthController {

    @GetMapping("/protectedJson.json")
    public ResponseEntity<Resource> getProtectedJson(HttpServletRequest request) throws IOException {
        // Check if the required cookies are present
        Cookie[] cookies = request.getCookies();
        if (cookies == null || !hasRequiredCookies(cookies)) {
            return ResponseEntity.status(401).body(null); // Unauthorized
        }

        // Load and serve the JSON file
        Path jsonFilePath = Paths.get("C:\\Swagger\\servers.json");
        Resource resource = new UrlResource(jsonFilePath.toUri());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + jsonFilePath.getFileName())
                .body(resource);
    }

    private boolean hasRequiredCookies(Cookie[] cookies) {
        return cookies[0].getValue().equalsIgnoreCase("Rushi") && cookies[1].getValue().equalsIgnoreCase("Tups");
//         Implement your logic to check for required cookies
//         Return true if the required cookies are present
//        return true;
    }
}
