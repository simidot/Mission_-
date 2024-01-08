package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    private String content;

    @CreationTimestamp
    private Timestamp createDate;

    private String password;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;


}
