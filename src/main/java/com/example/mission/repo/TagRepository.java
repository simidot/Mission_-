package com.example.mission.repo;

import com.example.mission.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    // 태그 내용으로 태그 찾기
    Tag findTagByContent(String content);
}
