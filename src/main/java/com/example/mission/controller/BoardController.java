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

import java.util.Optional;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

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
