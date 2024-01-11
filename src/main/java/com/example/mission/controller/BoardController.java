package com.example.mission.controller;

import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 1. 전체 게시판 카테고리 불러오기
    @GetMapping
    public String showBoardList(Model model) {
        model.addAttribute("boards", boardService.readBoardCategories());
        return "boardList";
    }


    // 1. 전체 카테고리 게시글 목록 보기
    @GetMapping("/allCategory")
    public String readAllCategoryArticles(Model model) {
        model.addAttribute("articles", boardService.readAllArticles());
        return "articleList";
    }

    // 1. 카테고리별 게시글 목록 보기
    @GetMapping("/{boardId}")
    public String readArticles(@PathVariable Long boardId, Model model) {
        BoardDto board = boardService.findByBoardId(boardId);
        model.addAttribute("boards", board);
        model.addAttribute("articles", boardService.readAllArticlesByBoardId(boardId));
        return "articleList";
    }

    // 5. 게시글 검색하기
    @GetMapping("/search")
    public String searchArticles(
            @RequestParam("criteria") String criteria,
            @RequestParam("search") String searchString,
            //카테고리가 없는 전체 게시판 검색을 위해 required = false로 설정하였다. 없으면 null값으로 들어간다.
            @RequestParam(name = "category", required = false) BoardCategory category,
            Model model
    ) {
        model.addAttribute("articles", boardService.searchArticles(criteria, searchString, category));
        return "searchResult";
    }
}