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
    // 보드 전체가 아니라,,, boardCategory만 갖고와도 되니까 이렇게 설정이 가능하다.
//    private String boardCategory;

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

    // static 정적 메서드 (클래스 생성 없이 사용할 수 있는 메서드)
    // 엔티티를 Dto로 바꾸는 메서드
    // static factory method 패턴
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
