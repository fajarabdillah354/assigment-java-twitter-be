package dev.codejar.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Timestamp createdAt;

}
