package com.example.mission.dto;

import com.example.mission.entity.ArticleTagMap;
import com.example.mission.entity.Tag;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class TagDto {
    private Long id;
    private String content;
    @Setter
    private Set<ArticleTagMapDto> articleSet = new HashSet<>();

    public static TagDto fromEntity(Tag entity) {
        TagDto dto = new TagDto();
        dto.id = entity.getId();
        dto.articleSet = new HashSet<>();
        for (ArticleTagMap map : entity.getArticleSet()) {
            dto.articleSet.add(ArticleTagMapDto.fromEntity(map));
        }
        return dto;
    }

}
