package com.example.mission.controller;

import com.example.mission.dto.CommentDto;
import com.example.mission.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성하기
    @PostMapping("/{articleId}/comment")
    public String createComment(@PathVariable("articleId") Long id,
                                @RequestParam("content") String content,
                                @RequestParam("password") String password,
                                RedirectAttributes redirectAttributes) {
        commentService.createComment(new CommentDto(content, password), id);
        redirectAttributes.addAttribute("articleId", id);
        return "redirect:/article/{articleId}";
    }
}
