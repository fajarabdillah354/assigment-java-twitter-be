package dev.codejar.repository;

import dev.codejar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, String> {


    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


    List<UserEntity> findByUsernameContainingIgnoreCase(String username);





}
