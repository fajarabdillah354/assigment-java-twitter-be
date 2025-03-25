package dev.codejar.repository;

import dev.codejar.entity.FollowerEntity;
import dev.codejar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FollowersRepository extends JpaRepository<FollowerEntity, String> {

    @Query("SELECT f FROM FollowerEntity f WHERE f.follower = :follower")
    List<FollowerEntity> getFolloweesByFollower(@Param("follower") UserEntity follower);

    @Query("SELECT f FROM FollowerEntity f WHERE f.followee = :followee")
    List<FollowerEntity> getFollowersByFollowee(@Param("followee") UserEntity followee);

    Optional<FollowerEntity> findByFollowerAndFollowee(UserEntity follower, UserEntity followee);


    @Modifying
    @Transactional
    @Query("DELETE FROM FollowerEntity f WHERE f.follower = :follower AND f.followee = :followee")
    void deleteByFollowerAndFollowee(@Param("follower") UserEntity follower,@Param("followee") UserEntity followee);

}
