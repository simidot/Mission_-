package com.example.mission.service;

import com.example.mission.dto.CommentDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Comment;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentDto createComment(CommentDto commentDto, Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        Comment comment = new Comment(commentDto.getContent(), commentDto.getPassword(), article);
        return CommentDto.fromEntity(commentRepository.save(comment));
    }
}
