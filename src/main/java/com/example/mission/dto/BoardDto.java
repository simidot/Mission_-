package com.example.mission.dto;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    @Setter
    private String category;
    private List<ArticleDto> articleDtoList = new ArrayList<>();

    public BoardDto(BoardCategory category) {
        this.category = String.valueOf(category);
    }

    public static BoardDto fromEntity(Board entity) {
        BoardDto dto = new BoardDto();
        dto.id = entity.getId();
        dto.category = entity.getCategory().name();
//        dto.articleDtoList = new ArrayList<>();
//        for (Article article : entity.getArticleList()) {
//            dto.articleDtoList.add(ArticleDto.fromEntity(article));
//        }
        return dto;
    }
}
