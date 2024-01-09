package com.example.mission.service;

import com.example.mission.dto.AllArticleDto;
import com.example.mission.dto.ArticleDto;
import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    // 게시판의 카테고리가 정해져있기 때문에 카테고리를 저장하는 메서드가 필요.
    public void createCategories() {
        // board 카테고리가 비어있을 때에만 추가!
        if (boardRepository.findAll().isEmpty()) {
            Board board1 = new Board(BoardCategory.자유);
            Board board2 = new Board(BoardCategory.개발);
            Board board3 = new Board(BoardCategory.일상);
            Board board4 = new Board(BoardCategory.사건사고);
            boardRepository.save(board1);
            boardRepository.save(board2);
            boardRepository.save(board3);
            boardRepository.save(board4);
        }
    }

    // 전체 게시판 카테고리 불러오기
    public List<BoardDto> readBoardCategories() {
        List<BoardDto> dtoList = new ArrayList<>();
        for (Board board : boardRepository.findAll()) {
            dtoList.add(BoardDto.fromEntity(board));
        }
        return dtoList;
    }

    // 게시판아이디로 게시판 카테고리 불러오기
    public BoardDto findByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        return BoardDto.fromEntity(board);
    }

    // 전체 카테고리 게시물 불러오기
    public List<AllArticleDto> readAllArticles() {
        List<AllArticleDto> dtoList = new ArrayList<>();
        for (Article article : articleRepository.findArticleByOrderByCreateDateDesc()) {
            dtoList.add(AllArticleDto.fromEntity(article));
        }
        return dtoList;
    }

    // 카테고리별 게시물 전체 불러오기
    public List<AllArticleDto> readAllArticlesByBoardId(Long boardId) {
        List<AllArticleDto> dtoList = new ArrayList<>();
        for (Article article : articleRepository.findArticleByBoardIdOrderByCreateDateDesc(boardId)) {
            dtoList.add(AllArticleDto.fromEntity(article));
        }
        return dtoList;
    }

    // 게시글 id로 board 찾기
    public Board findBoardByArticleId(Long articleId) {
        return boardRepository.findBoardByArticleListId(articleId);
    }


}
