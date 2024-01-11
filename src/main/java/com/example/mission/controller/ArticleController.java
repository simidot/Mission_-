package com.example.mission.controller;

import com.example.mission.dto.ArticleDto;
import com.example.mission.entity.BoardCategory;
import com.example.mission.service.ArticleService;
import com.example.mission.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final BoardService boardService;

    // 2. 게시글 작성하기 폼으로 이동(view)
    @GetMapping
    public String createArticleForm() {
        return "createArticleForm";
    }

    // 2. 게시글 작성하기(post)
    @PostMapping
    public String createArticle(
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("category") BoardCategory category,
            @RequestParam("password") String password
    ) {
        articleService.createArticle(new ArticleDto(title, content, password), category);
        return "redirect:/boards";
    }

    // 3. 게시글 id값 받아와서 게시글 상세보기 + 댓글 조회하기
    @GetMapping("/{articleId}")
    public String viewArticleDetail(@PathVariable("articleId") Long id, Model model) {
        ArticleDto article = articleService.viewArticleDetail(id);
        model.addAttribute("article", article);
        model.addAttribute("board", boardService.findBoardByArticleId(id));
        return "articleDetail";
    }

    // 3. 게시글 수정하기 폼으로 이동 (view)
    @GetMapping("/{articleId}/update")
    public String updateArticleForm(@PathVariable("articleId") Long id, Model model) {
        model.addAttribute("article", articleService.viewArticleDetail(id));
        model.addAttribute("board", boardService.findBoardByArticleId(id));
        return "updateArticleForm";
    }

    // 3. 게시글 수정하기
    // todo: 고민거리... 비밀번호 일치여부에 따른 로직은 컨트롤러 단에서 해야할까 서비스단에서 해야할까..?
    @PostMapping("/{articleId}/update")
    public String updateArticle(@RequestParam("id") Long id,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("category") BoardCategory category,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes
    ) {
        articleService.updateArticle(new ArticleDto(id, title, content, password), category);
        redirectAttributes.addAttribute("articleId", id);

        return "redirect:/article/{articleId}";
    }

    //3. 게시글 삭제하기
    @GetMapping("/{articleId}/delete")
    public String deleteArticle(@PathVariable("articleId") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/boards";
    }
}