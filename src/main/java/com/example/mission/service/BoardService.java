package com.example.mission.service;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    // 전체 게시판 카테고리 불러오기
    // 게시판의 카테고리가 정해져있기 때문에 불러오면서 바로 카테고리를 저장하도록 했다.
    // todo: 고민점은 카테고리를 저장하는게 맞는지가 ? 고민... 확장성도 고려하고 싶은데..
    public List<Board> readBoardCategories() {
        Board board1 = Board.builder().category(BoardCategory.자유).build();
        Board board2 = Board.builder().category(BoardCategory.개발).build();
        Board board3 = Board.builder().category(BoardCategory.일상).build();
        Board board4 = Board.builder().category(BoardCategory.사건사고).build();
        boardRepository.save(board1);
        boardRepository.save(board2);
        boardRepository.save(board3);
        boardRepository.save(board4);
        return boardRepository.findAll();
    }

    // 게시판아이디로 게시판 카테고리 불러오기
    public Optional<Board> findByBoardId(Long boardId) {
        return boardRepository.findById(boardId);
    }

    // 전체 게시물 불러오기
    public List<Article> readAllArticles() {
        return articleRepository.findAll();
    }

    // 카테고리별 게시물 전체 불러오기
    public List<Article> readAllArticlesByBoardId(Long boardId) {
        return articleRepository.findArticleByBoardIdOrderByCreateDate(boardId);
    }


}
