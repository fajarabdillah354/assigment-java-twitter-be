package dev.codejar.service;


import dev.codejar.entity.UserActivityEntity;
import dev.codejar.entity.UserEntity;
import dev.codejar.repository.UsersActivityRepository;
import dev.codejar.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    private final UsersActivityRepository usersActivityRepository;

    public UserService(UsersRepository usersRepository, UsersActivityRepository usersActivityRepository) {
        this.usersRepository = usersRepository;
        this.usersActivityRepository = usersActivityRepository;
    }


    public List<UserEntity> listUsers(){
        return usersRepository.findAll();
    }


//    public boolean isUsernameTaken(String username) {
//        return usersRepository.existsByUsername(username);
//    }
//
//    public boolean isEmailTaken(String email) {
//        return usersRepository.existsByEmail(email);
//    }
//
//    public UserEntity saveUser(UserEntity user) {
//        return usersRepository.save(user);
//    }


    public List<UserEntity> searchUsers(String username) {
        List<UserEntity> users = usersRepository.findByUsernameContainingIgnoreCase(username);
        return users.stream()
                .sorted(Comparator.comparingInt((UserEntity u) ->
                                usersActivityRepository.findByUser(u)
                                        .map(UserActivityEntity::getPostCount)
                                        .orElse(0))
                        .reversed()
                        .thenComparing(u -> usersActivityRepository.findByUser(u)
                                .map(UserActivityEntity::getLastActive)
                                .orElse(LocalDateTime.MIN), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }


}
