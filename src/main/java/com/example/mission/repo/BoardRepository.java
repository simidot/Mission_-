package com.example.mission.repo;

import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 게시글 작성 폼에서 입력받은 카테고리로 Board 객체 찾는 메서드
    Board findBoardByCategory(BoardCategory category);

    //    Board findBoardByArticleId(Long id);
    Board findBoardByArticleListId(Long articleId);

}
