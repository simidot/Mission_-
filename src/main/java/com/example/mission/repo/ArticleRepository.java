package com.example.mission.repo;

import com.example.mission.entity.Article;
import com.example.mission.entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // SELECT * FROM article LEFT JOIN board ON article.board = board.id where board.id=?
    List<Article> findArticleByBoardIdOrderByCreateDateDesc(@Param("boardId") Long id);

    // SELECT * FROM article ORDER BY createDate
    List<Article> findArticleByOrderByCreateDateDesc();


    // 제목 기준으로 게시글 찾기
    List<Article> findArticlesByTitleContaining(@Param("searchString") String searchString);

    // 내용 기준으로 게시글 찾기
    List<Article> findArticlesByContentContaining(@Param("searchString") String searchString);

    // 상관없이 게시글 찾기
    List<Article> findArticlesByTitleContainingOrContentContaining(String keyword1, String keyword2);

    // 카테고리와 제목 기준으로 게시글 찾기
    List<Article> findArticlesByTitleContainingAndBoard_Category(String keyword, BoardCategory category);
    // 카테고리와 내용 기준으로 게시글 찾기
    List<Article> findArticlesByContentContainingAndBoard_Category(String keyword, BoardCategory category);

    // 카테고리 기준으로 게시글 찾기
    List<Article> findArticlesByContentContainingOrContentContainingAndBoard_Category(String keyword1, String keyword2, BoardCategory category);

}

