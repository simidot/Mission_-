package com.example.mission.dto;

import com.example.mission.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
public class UpdateArticleDto {
    private Long id;
    @Setter
    private String title;
    @Setter
    private String content;
    private Timestamp createDate;
    @Setter
    private String password;
    private BoardDto board;
    public UpdateArticleDto(Long id, String title, String content, String password) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.password = password;
    }

//    public UpdateArticleDto(Long id, String title, String content, BoardDto board) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.board = board;
//    }

    public static UpdateArticleDto fromEntity(Article entity) {
        UpdateArticleDto dto = new UpdateArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        dto.createDate = entity.getCreateDate();
        dto.board = BoardDto.fromEntity(entity.getBoard());
        return dto;
    }
}
