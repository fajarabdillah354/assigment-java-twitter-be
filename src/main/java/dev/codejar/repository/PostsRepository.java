package dev.codejar.repository;

import dev.codejar.entity.PostEntity;
import dev.codejar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostEntity, String> {

    List<PostEntity> findByUser(UserEntity users);


    List<PostEntity> findByUserInOrderByCreatedAtDesc(List<UserEntity> users);

}
