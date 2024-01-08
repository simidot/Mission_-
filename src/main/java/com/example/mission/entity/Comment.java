package com.example.mission.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String password;
    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne
    private Article article;
}
