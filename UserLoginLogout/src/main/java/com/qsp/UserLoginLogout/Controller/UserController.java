package com.qsp.UserLoginLogout.Controller;

import com.qsp.UserLoginLogout.Entity.User;
import com.qsp.UserLoginLogout.Service.CloudinaryService;
import com.qsp.UserLoginLogout.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryService cloudinaryService;

    //  Add User with image
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addUser(
            @Valid @ModelAttribute User user,
            @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request Must Contain file");
            }

            String contentType = file.getContentType();
            if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPG and PNG allowed");
            }

            String fileUrl = cloudinaryService.uploadFile(file);
            user.setFileUrl(fileUrl);
            userService.savedUser(user);
            return ResponseEntity.ok("User added successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    //  Get all users
    @GetMapping("/viewall")
    public ResponseEntity<List<User>> getallUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    //  Get user by id
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Optional<User>> getUserByid(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUserByid(id));
    }


    @PutMapping("/updateByid/{id}")
    public ResponseEntity<User> updateByid(@PathVariable int id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateByid(id, user));
    }


    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Optional<User>> deleteByid(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteByid(id));
    }
}