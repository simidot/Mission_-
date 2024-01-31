package com.example.mission.repo;

import com.example.mission.entity.Article;
import com.example.mission.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // SELECT * FROM article LEFT JOIN board ON article.board = board.id where board.id=?
    // 게시판 카테고리별 게시글 모아보기 (최신순 정렬)
    List<Article> findAllByBoardIdOrderByCreateDateDesc(@Param("boardId") Long id);

    // SELECT * FROM article ORDER BY createDate
    // 전체게시판 게시글 모아보기 (최신순 정렬)
    List<Article> findAllByOrderByCreateDateDesc();


    // 제목 기준으로 게시글 찾기
    List<Article> findAllByTitleContaining(@Param("searchString") String searchString);

    // 내용 기준으로 게시글 찾기
    List<Article> findAllByContentContaining(@Param("searchString") String searchString);

    // 상관없이 게시글 찾기
    List<Article> findAllByTitleContainingOrContentContaining(String keyword1, String keyword2);

    // 카테고리와 제목 기준으로 게시글 찾기
    List<Article> findAllByTitleContainingAndBoard_Category(String keyword, BoardCategory category);
    // 카테고리와 내용 기준으로 게시글 찾기
    List<Article> findAllByContentContainingAndBoard_Category(String keyword, BoardCategory category);

    // 카테고리 기준으로 게시글 찾기
    List<Article> findAllByTitleContainingOrContentContainingAndBoard_Category(String keyword1, String keyword2, BoardCategory category);


}

