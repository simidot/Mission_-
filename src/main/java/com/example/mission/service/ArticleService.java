package com.example.mission.service;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    // 게시글 저장 메서드
    public void createArticle(String title, String content, BoardCategory category, String password) {
        Article article = new Article();
        Board foundBoard = boardRepository.findBoardByCategory(category);
        article.setTitle(title);
        article.setContent(content);
        article.setBoard(foundBoard);
        article.setPassword(password);
        articleRepository.save(article);
    }
}
