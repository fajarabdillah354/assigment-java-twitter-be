package dev.codejar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "followers")
public class FollowerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "followee_id", nullable = false)
    private UserEntity followee;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();


}
