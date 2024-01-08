package com.example.mission.controller;

import com.example.mission.entity.Article;
import com.example.mission.entity.BoardCategory;
import com.example.mission.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성하기 폼으로 이동(view)
    @GetMapping
    public String createArticleForm() {
        return "createArticleForm";
    }

    // 게시글 작성하기(post)
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

    // 게시글 id값 받아와서 게시글 상세보기
    @GetMapping("/{articleId}")
    public String viewArticleDetail(@PathVariable("articleId") Long id, Model model) {
        Article article = articleService.viewArticleDetail(id).orElse(null);
        model.addAttribute("article", article);
        return "articleDetail";
    }
}
