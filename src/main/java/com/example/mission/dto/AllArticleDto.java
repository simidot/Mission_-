package com.example.mission.dto;

import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class AllArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    private Timestamp createDate;
    @Setter
    private String password;
    private BoardDto board;
    public AllArticleDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public AllArticleDto (Long id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public static AllArticleDto fromEntity(Article entity) {
        AllArticleDto dto = new AllArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        dto.createDate = entity.getCreateDate();
        dto.board = BoardDto.fromEntity(entity.getBoard());
        return dto;
    }
}
