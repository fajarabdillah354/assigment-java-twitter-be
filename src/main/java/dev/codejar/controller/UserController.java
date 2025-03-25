package dev.codejar.controller;


import dev.codejar.entity.UserEntity;
import dev.codejar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<UserEntity> userEntities(){
        return userService.listUsers();
    }



    @GetMapping("/search")
    public ResponseEntity<List<UserEntity>> searchUsers(@RequestParam String username) {
        return ResponseEntity.ok(userService.searchUsers(username));
    }


}
