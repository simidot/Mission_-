package com.example.mission.dto;

import com.example.mission.entity.ArticleTagMap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@NoArgsConstructor
public class ArticleTagMapDto {
    private Long id;

    public static ArticleTagMapDto fromEntity(ArticleTagMap entity) {
        ArticleTagMapDto dto = new ArticleTagMapDto();
        dto.id = entity.getId();
        return dto;
    }

}
