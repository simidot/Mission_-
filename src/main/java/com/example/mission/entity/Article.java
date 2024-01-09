package com.example.mission.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Lob
    private String content;

    @CreationTimestamp
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
//    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="Asia/Seoul") //날짜 포멧 바꾸기
    private Timestamp createDate;

    private String password;

    @ManyToOne
    private Board board;

    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;

    public Article() {

    }

    //연관관계 편의 메서드
    public void addBoard(Board board) {
        // article에 이미 board가 설정되어 있을 경우
        if (this.board != null) {
            // board에서 해당 Entity 제거
            this.board.getArticleList().remove(this);
        }
        // 해당 article Entity에 파라미터로 들어온 board 연관관계 설정
        this.board = board;
        // 파라미터로 들어온 board Entity에 article 연관관계 설정
        board.getArticleList().add(this);
    }

}