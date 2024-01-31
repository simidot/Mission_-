package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class Board extends BaseEntity{
    // PK를 개발자가 임의적으로 수정할 수 없도록 @Setter를 설정해주지 않는다.
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