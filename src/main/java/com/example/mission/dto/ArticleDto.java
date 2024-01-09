package com.example.mission.dto;

import com.example.mission.entity.Article;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Timestamp createDate;
    private String password;


    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createDate = article.getCreateDate();
        this.password = article.getPassword();
    }
}
