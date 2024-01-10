package com.example.mission.service;

import com.example.mission.dto.AllArticleDto;
import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

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
