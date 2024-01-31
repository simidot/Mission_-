package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity{

    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    public Comment(String content, String password, Article article) {
        this.content = content;
        this.password = password;
        this.article = article;
    }
}
