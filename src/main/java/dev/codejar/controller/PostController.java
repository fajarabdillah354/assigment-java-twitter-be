package dev.codejar.controller;


import dev.codejar.entity.PostEntity;
import dev.codejar.entity.UserEntity;
import dev.codejar.payload.request.PostRequest;
import dev.codejar.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostEntity> createPost(@AuthenticationPrincipal UserEntity user,
                                                    @RequestBody PostRequest request) {
        PostEntity newPost = postService.createPost(user.getId(), request.getContent());
        return ResponseEntity.ok(newPost);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<PostEntity>> getFeed(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(postService.getFeed(user.getId()));
    }

    @GetMapping("/user-post-list")
    public List<PostEntity> postList(){
        return postService.getListPost();
    }

}
