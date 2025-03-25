package dev.codejar.controller;


import dev.codejar.entity.UserEntity;
import dev.codejar.service.FollowerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/follow")
public class FollowerController {


    private final FollowerService followerService;


    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @PostMapping("/{followeeId}")
    public ResponseEntity<String> followUser(@RequestParam String followerId, @PathVariable String followeeId) {
        return ResponseEntity.ok(followerService.followUser(followerId, followeeId));
    }

    @GetMapping("/followees")
    public ResponseEntity<?> getFollowees(@RequestParam String userId) {
        List<UserEntity> followees = followerService.getFollowees(userId);

        if (followees.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This user is not following anyone yet.");
        }

        return ResponseEntity.ok(followees);
    }



    @GetMapping("/followers")
    public ResponseEntity<List<UserEntity>> getFollowers(@RequestParam String userId) {
        return ResponseEntity.ok(followerService.getFollowers(userId));
    }


    @DeleteMapping("/{followeeId}")
    public ResponseEntity<String> unfollowUser(@RequestParam String followerId, @PathVariable String followeeId) {
        String response = followerService.unfollowUser(followerId, followeeId);
        if (response.equals("Successfully unfollowed user")) {
            return ResponseEntity.ok().body("success unfollow user");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


}
