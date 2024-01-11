package com.example.mission.service;

import com.example.mission.dto.*;
import com.example.mission.entity.*;
import com.example.mission.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final TagRepository tagRepository;
    private final ArticleTagMapRepository articleTagMapRepository;

    // 2. 게시글 저장 메서드
    @Transactional
    public void createArticle(ArticleDto articleDto, BoardCategory category) {
        Board board = boardRepository.findBoardByCategory(category);
        Article article = new Article(articleDto.getTitle(), articleDto.getContent(),
                articleDto.getPassword(), board);
        ArticleDto.fromEntity(articleRepository.save(article));
        ArticleDto newDto = new ArticleDto(article.getId(), article.getTitle(), article.getContent(),
                article.getPassword());
        createTagList(newDto, newDto);
    }

    // 3. 게시글 수정하기
    @Transactional
    public void updateArticle(ArticleDto articleDto, BoardCategory category) {
        // 게시글 id로 가져온 게시글
        Article originalArticle = articleRepository.findById(articleDto.getId()).orElseThrow();
        // 카테고리가 변경되었다면 필요한 보드
        Board updatedBoard = boardRepository.findBoardByCategory(category);
        // 바꿀 내용이 적용된 게시글Dto
        ArticleDto newDto = new ArticleDto(articleDto.getId(), articleDto.getTitle(), articleDto.getContent(),
                articleDto.getPassword(), articleDto.getTagSet());

        // 내용 중 태그 내용 변경 (인자로 변경 예정 내용과 현재 저장된 내용을 함께 가져감)
        createTagList(newDto, ArticleDto.fromEntity(originalArticle));

        // 변경사항 적용 후 저장
        originalArticle.setBoard(updatedBoard);
        originalArticle.setTitle(articleDto.getTitle());
        originalArticle.setContent(articleDto.getContent());
        ArticleDto.fromEntity(articleRepository.save(originalArticle));

    }

    // 3. 게시글 상세 조회
    public ArticleDto viewArticleDetail(Long id) {
        Article article = articleRepository.findById(id).orElseThrow();
        return ArticleDto.fromEntity(article);
    }


    // 3. 게시글 삭제하기
    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            List<ArticleTagMap> articleTagMap = articleTagMapRepository.findArticleTagMapsByArticleId(article.getId());
            articleRepository.delete(article);
            articleTagMapRepository.deleteAll(articleTagMap);
        }
    }


    // 6. 게시글에서 태그 추출하는 메서드
    private void createTagList(ArticleDto newDto, ArticleDto originalDto) {
        // 글 내용(content)에서 해시태그 부분 파싱
        Pattern myPattern = Pattern.compile("#(\\S+)");
        Matcher matcher = myPattern.matcher(newDto.getContent());
        Set<String> tags = new HashSet<>();
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }
        saveTag(tags, originalDto);
    }

    // 6. 추출된 태그 확인하고 저장하는 메서드
    private void saveTag(Set<String> newTagSet, ArticleDto originalDto) {
        Article article = articleRepository.findById(originalDto.getId()).orElseThrow();
        Set<ArticleTagMap> originalTagSet = article.getTagSet();
        System.out.println("orgiinalTagSet사이즈....." + originalTagSet.size());

        for (String tagContent : newTagSet) {
            Tag result = tagRepository.findTagByContent(tagContent);
            Tag newTag;
            // 등록이 되어있는 태그가 아니라면 태그 추가

            if (result==null) {
                newTag = new Tag(tagContent);
                tagRepository.save(newTag);
            } else {
                System.out.println("찾은 태그가 몬댕???"+result.getContent());
                newTag = result;
            }
            ArticleTagMap newTagMap = new ArticleTagMap(article, newTag);
            if (originalTagSet.isEmpty()) {
                articleTagMapRepository.save(newTagMap);
            } else {
                if (!(originalTagSet.contains(newTagMap))) {
                    articleTagMapRepository.save(new ArticleTagMap(article, newTag));
                } else {
                    break;
                }
            }
        }
    }

}