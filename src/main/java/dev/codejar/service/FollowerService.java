package dev.codejar.service;


import dev.codejar.entity.FollowerEntity;
import dev.codejar.entity.UserEntity;
import dev.codejar.repository.FollowersRepository;
import dev.codejar.repository.UsersRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowerService {

    private final FollowersRepository followersRepository;
    private final UsersRepository usersRepository;

    public FollowerService(FollowersRepository followersRepository, UsersRepository usersRepository) {
        this.followersRepository = followersRepository;
        this.usersRepository = usersRepository;
    }

    public String followUser(String followerId, String followeeId) {
        if (followerId.equals(followeeId)) {
            throw new IllegalArgumentException("You cannot follow yourself");
        }

        UserEntity follower = usersRepository.findById(followerId)
                .orElseThrow(() -> new UsernameNotFoundException("Follower not found"));
        UserEntity followee = usersRepository.findById(followeeId)
                .orElseThrow(() -> new UsernameNotFoundException("Followee not found"));

        if (followersRepository.findByFollowerAndFollowee(follower, followee).isPresent()) {
            return "You already follow this user";
        }

        FollowerEntity newFollow = new FollowerEntity();
        newFollow.setFollower(follower);
        newFollow.setFollowee(followee);
        newFollow.setCreatedAt(LocalDateTime.now());

        followersRepository.save(newFollow);
        return "Successfully followed user";
    }


    @Transactional
    public String unfollowUser(String followerId, String followeeId) {
        UserEntity follower = usersRepository.findById(followerId)
                .orElseThrow(() -> new UsernameNotFoundException("Follower not found"));
        UserEntity followee = usersRepository.findById(followeeId)
                .orElseThrow(() -> new UsernameNotFoundException("Followee not found"));

        Optional<FollowerEntity> followRelation = followersRepository.findByFollowerAndFollowee(follower, followee);
        if (followRelation.isPresent()) {
            followersRepository.deleteByFollowerAndFollowee(follower, followee);
            return "Successfully unfollowed user";
        } else {
            return "You are not following this user";
        }
    }


    public List<UserEntity> getFollowees(String userId) {
        UserEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<FollowerEntity> followees = followersRepository.getFolloweesByFollower(user);
        System.out.println("Jumlah followees ditemukan: " + followees.size());

        return followees.stream()
                .map(FollowerEntity::getFollowee)
                .collect(Collectors.toList());
    }



    public List<UserEntity> getFollowers(String userId) {
        UserEntity user = usersRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return followersRepository.getFollowersByFollowee(user).stream()
                .map(FollowerEntity::getFollower)
                .collect(Collectors.toList());
    }

}
