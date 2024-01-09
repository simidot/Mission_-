package com.example.mission.dto;

import com.example.mission.entity.Comment;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @Setter
    private String content;
    @Setter
    private String password;

    public static CommentDto fromEntity(Comment entity) {
        CommentDto dto = new CommentDto();
        dto.id = entity.getId();
        dto.content = entity.getContent();
        dto.password = entity.getPassword();
        return dto;
    }
}
