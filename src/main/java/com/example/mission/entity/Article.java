package com.example.mission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;
    @Lob
    @Setter
    private String content;

    @CreationTimestamp
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul") //날짜 포멧 바꾸기
    private Timestamp createDate;

    private String password;

    @ManyToOne
    @Setter
    private Board board;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Comment> commentList = new ArrayList<>();

    public Article(String title, String content, String password, Board board) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.board = board;
    }

}