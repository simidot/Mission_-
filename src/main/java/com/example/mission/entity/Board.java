package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Board(BoardCategory category) {
        this.category = category;
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private BoardCategory category;

    @OneToMany(mappedBy = "board")
    private List<Article> articleList;

}