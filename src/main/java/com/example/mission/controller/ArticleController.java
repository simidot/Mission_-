package com.example.mission.controller;

import com.example.mission.entity.Article;
import com.example.mission.entity.BoardCategory;
import com.example.mission.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성하기 폼으로 이동
    @GetMapping
    public String createArticleForm() {
        return "createArticleForm";
    }

    @PostMapping
    public String createArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("category") BoardCategory category,
            @RequestParam("password") String password
    ) {
        articleService.createArticle(title, content, category, password);
        return "redirect:/boards";
    }
}
