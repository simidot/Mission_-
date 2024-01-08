package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public Board(BoardCategory category) {
        this.category = category;
    }

    @Enumerated(EnumType.STRING)
    private BoardCategory category;

    @OneToMany(mappedBy = "board")
    private List<Article> articleList;

}
