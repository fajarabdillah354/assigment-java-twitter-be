package dev.codejar.repository;

import dev.codejar.entity.TokenEntity;
import dev.codejar.entity.TokenType;
import dev.codejar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokensRepository extends JpaRepository<TokenEntity, String> {

    Optional<TokenEntity> findByAccessToken(String token);


    @Query("SELECT t FROM TokenEntity t WHERE t.user.id = :id")
    List<TokenEntity> findAllByUserId(@Param("id") String id);

    void deleteByUser(UserEntity user);

    @Query("SELECT t FROM TokenEntity t WHERE t.user.id = :id")
    List<TokenEntity> findAllIsValidTokenByUser(@Param("id") String id);

    Optional<TokenEntity> findFirstByUserAndTokenTypeAndRevokedFalseAndExpiredFalse(UserEntity user, TokenType tokenType);

}
