package com.example.mission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Article extends BaseEntity{

    @Setter
    private String title;
    @Lob
    @Setter
    private String content;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @Setter
    private Board board;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleTagMap> tagSet = new HashSet<>();

    @Builder
    public Article(String title, String content, String password, Board board) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.board = board;
    }

}