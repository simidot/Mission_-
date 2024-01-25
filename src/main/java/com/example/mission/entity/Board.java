package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Board {
    // PK를 개발자가 임의적으로 수정할 수 없도록 @Setter를 설정해주지 않는다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder
    public Board(BoardCategory category) {
        this.category = category;
    }

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @Setter
    private BoardCategory category;

    @OneToMany(mappedBy = "board")
    private List<Article> articleList;

}