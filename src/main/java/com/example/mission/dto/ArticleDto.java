package com.example.mission.dto;

import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    private Timestamp createDate;
    @Setter
    private String password;
    private List<CommentDto> comments = new ArrayList<>();

    public ArticleDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public ArticleDto(Long id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }

    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        dto.createDate = entity.getCreateDate();
        dto.comments = new ArrayList<>();
        for (Comment comment : entity.getCommentList()) {
            dto.comments.add(CommentDto.fromEntity(comment));
        }
        return dto;
    }
}
