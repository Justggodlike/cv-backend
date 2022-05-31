package com.epam.cv.controller;

import com.epam.cv.dto.FileDto;
import com.epam.cv.dto.MessageDto;
import com.epam.cv.entity.User;
import com.epam.cv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/me")
    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    @CrossOrigin
    @PostMapping("/user/uploadFile")
    public String uploadFile() throws IOException {
        return userService.saveFile();
    }

    @CrossOrigin
    @GetMapping("/user/getFile/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable String fileId) {
        FileDto file = userService.serveFile(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName() + "")
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Access-Token, Uid")
                .body(file.getBytes());
    }

}
