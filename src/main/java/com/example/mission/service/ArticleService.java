package com.example.mission.service;

import com.example.mission.dto.ArticleDto;
import com.example.mission.dto.BoardDto;
import com.example.mission.dto.UpdateArticleDto;
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
    public ArticleDto createArticle(ArticleDto articleDto, BoardCategory category) {
        Board board = boardRepository.findBoardByCategory(category);
        Article article = new Article(articleDto.getTitle(), articleDto.getContent(),
                articleDto.getPassword(), board);
        return ArticleDto.fromEntity(articleRepository.save(article));
    }

    // 게시글 상세 조회
    public ArticleDto viewArticleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }

    // 게시글 수정하기
    public void updateArticle(UpdateArticleDto articleDto, BoardCategory category) {
        Article article = articleRepository.findById(articleDto.getId()).orElseThrow();

        // 비밀번호가 올렸던 당시의 비밀번호와 일치하면 수정 가능
        if (article.getPassword().equals(articleDto.getPassword())) {
            Board foundBoard = boardRepository.findBoardByArticleListId(articleDto.getId());
            foundBoard.setCategory(category);
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
            article.setBoard(foundBoard);

            UpdateArticleDto.fromEntity(articleRepository.save(article));
        } else { //일치하지 않으면 todo: 일치하지 않으면 어떻게 해야할지?
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
    }

    // 게시글 삭제하기
    public void deleteArticle(Long id) {
        Article delete = articleRepository.findById(id).orElse(null);
        articleRepository.delete(delete);
    }
}