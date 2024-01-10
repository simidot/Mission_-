package com.example.mission.controller;

import com.example.mission.dto.BoardDto;
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
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 전체 게시판 카테고리 불러오기
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", boardService.readBoardCategories());
        return "boardList";
    }


    // 전체 카테고리 게시글 목록 보기
    @GetMapping("/allCategory")
    public String readAllCategoryArticles(Model model) {
        model.addAttribute("articles", boardService.readAllArticles());
        return "articleList";
    }

    // 카테고리별 게시글 목록 보기
    @GetMapping("/{boardId}")
    public String readArticles(@PathVariable Long boardId, Model model) {
        BoardDto board = boardService.findByBoardId(boardId);
        model.addAttribute("boards", board);
        model.addAttribute("articles", boardService.readAllArticlesByBoardId(boardId));
        return "articleList";
    }
}