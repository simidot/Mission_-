package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ArticleTagMap extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    private Tag tag;

    @Builder
    public ArticleTagMap(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }
}
