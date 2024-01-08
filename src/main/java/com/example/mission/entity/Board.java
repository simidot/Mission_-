package com.example.mission.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    @OneToMany(mappedBy = "board")
    private List<Article> articleList;


}
