package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ArticleTagMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    public ArticleTagMap(Long id, Article article, Tag tag) {
        this.id = id;
        this.article = article;
        this.tag = tag;
    }

    public ArticleTagMap(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }
}
