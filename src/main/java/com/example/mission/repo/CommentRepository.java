package com.example.mission.repo;

import com.example.mission.dto.CommentDto;
import com.example.mission.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
