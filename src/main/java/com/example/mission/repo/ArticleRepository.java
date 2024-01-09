package com.example.mission.repo;

import com.example.mission.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // SELECT * FROM article LEFT JOIN board ON article.board = board.id where board.id=?
    List<Article> findArticleByBoardIdOrderByCreateDateDesc(@Param("boardId") Long id);

    // SELECT * FROM article ORDER BY createDate
    List<Article> findArticleByOrderByCreateDateDesc();




}

