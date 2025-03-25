package dev.codejar.service;

import dev.codejar.entity.FollowerEntity;
import dev.codejar.entity.PostEntity;
import dev.codejar.entity.UserActivityEntity;
import dev.codejar.entity.UserEntity;
import dev.codejar.exception.UserNotFoundException;
import dev.codejar.repository.FollowersRepository;
import dev.codejar.repository.PostsRepository;
import dev.codejar.repository.UsersActivityRepository;
import dev.codejar.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostsRepository postsRepository;

    private final FollowersRepository followersRepository;

    private final UsersRepository usersRepository;

    private final UsersActivityRepository usersActivityRepository;

    public PostService(PostsRepository postsRepository, FollowersRepository followersRepository, UsersRepository usersRepository, UsersActivityRepository usersActivityRepository) {
        this.postsRepository = postsRepository;
        this.followersRepository = followersRepository;
        this.usersRepository = usersRepository;
        this.usersActivityRepository = usersActivityRepository;
    }


    @Transactional
    public PostEntity createPost(String userId, String content) {
        UserEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        PostEntity post = new PostEntity();
        post.setUser(user);
        post.setContent(content);
        post.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // Store post to database
        PostEntity savedPost = postsRepository.save(post);

        // Update aktivity user (post count)
        UserActivityEntity userActivity = usersActivityRepository.findByUser(user)
                .orElse(new UserActivityEntity());
        userActivity.setUser(user);
        userActivity.setPostCount(userActivity.getPostCount() + 1);
        usersActivityRepository.save(userActivity);

        return savedPost;
    }


    public List<PostEntity> getListPost(){
        return postsRepository.findAll();
    }



    public List<PostEntity> getFeed(String userId) {
        UserEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

        List<UserEntity> followees = followersRepository.getFollowersByFollowee(user).stream()
                .map(FollowerEntity::getFollowee)
                .collect(Collectors.toList());

        return postsRepository.findByUserInOrderByCreatedAtDesc(followees);
    }
}
