package com.example.mission.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private Timestamp createDate;
    private String password;
}
