package com.example.mission.service;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    // 게시글 상세 조회
    public Optional<Article> viewArticleDetail(Long id) {
        return articleRepository.findById(id);
    }

    // 게시글 수정하기
    public void updateArticle(Long id, String title, String content, BoardCategory category, String password) {
        Article article = articleRepository.findById(id).orElse(null);

        // 비밀번호가 올렸던 당시의 비밀번호와 일치하면 수정 가능
        if (article.getPassword().equals(password)) {
            Board foundBoard = boardRepository.findBoardByArticleListId(id);
            foundBoard.setCategory(category);
            article.setTitle(title);
            article.setContent(content);

            articleRepository.save(article);
            boardRepository.save(foundBoard);
        } else { //일치하지 않으면
            System.out.println("비밀번호가 일치하지 않습니다.");
        }

    }
}