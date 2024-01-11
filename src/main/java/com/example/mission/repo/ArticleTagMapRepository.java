package com.example.mission.repo;

import com.example.mission.entity.ArticleTagMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleTagMapRepository extends JpaRepository<ArticleTagMap, Long> {
    // 게시글 id로 게시글태그맵 찾기
    List<ArticleTagMap> findArticleTagMapsByArticleId(Long id);
}
