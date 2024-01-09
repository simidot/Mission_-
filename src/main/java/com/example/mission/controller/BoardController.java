package com.example.mission.controller;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    // BoardController가 생성될 때 카테고리가 저장되도록 한다.
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
        boardService.createCategories(); //카테고리 insert 메서드
    }

    // todo: 게시판 목록 보기에서 새로고침시 계속 카테고리가 추가되는 문제 > 일단 해결
    // 생성자에 create문을 따로 분리하고, 서비스단에서 if문으로 조건을 걸어서 해결함

    // 게시판 목록 전체 보기
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", boardService.readBoardCategories());
        return "boardList";
    }

    // 카테고리별 게시글 목록 보기
    @GetMapping("/{boardId}")
    public String readArticles(@PathVariable Long boardId, Model model) {
        Board board = boardService.findByBoardId(boardId).orElse(null);
        model.addAttribute("boards", board);
        model.addAttribute("articles", boardService.readAllArticlesByBoardId(boardId));
        return "articleList";
    }

    // 전체 카테고리 게시글 목록 보기
    @GetMapping("/allCategory")
    public String readAllCategoryArticles(Model model) {
        model.addAttribute("articles", boardService.readAllArticles());
        return "articleList";
    }
}