package dev.codejar.repository;

import dev.codejar.entity.UserActivityEntity;
import dev.codejar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersActivityRepository extends JpaRepository<UserActivityEntity, String> {

    Optional<UserActivityEntity> findByUser(UserEntity users);

    List<UserActivityEntity> findAllByOrderByPostCountDescLastActiveDesc();

}
