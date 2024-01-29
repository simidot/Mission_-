package com.example.mission.repo;

import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@TestPropertySource(properties = "spring.sql.init.mode=never")
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ArticleRepository articleRepository;

    private List<Board> boardsToSave;
    private List<Board> boardList;

    @BeforeEach
    @Transactional
    void createDefaultBoards() {
        boardsToSave = new ArrayList<>();
        boardsToSave.addAll(Arrays.asList(
                Board.builder().category(BoardCategory.개발).build(),
                Board.builder().category(BoardCategory.자유).build(),
                Board.builder().category(BoardCategory.일상).build(),
                Board.builder().category(BoardCategory.사건사고).build()));
        boardList = boardRepository.saveAll(boardsToSave);
    }

    @AfterEach
    @Transactional
    void cleanUp() {
        boardRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    @DisplayName("전체_게시판_객체_찾기")
    @Transactional
    void findAll() {
        //given

        //when
        List<Board> result = boardRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));

        //then
        assertNotNull(result);
        assertEquals(boardsToSave.size(), result.size());
        for (int i = 0; i < boardsToSave.size(); i++) {
            assertEquals(boardsToSave.get(i).getCategory(), result.get(i).getCategory());
        }
    }

    @Test
    @DisplayName("게시판id로_게시판_객체_찾기")
    @Transactional
    void findById() {
        //given
        Long boardId = boardList.get(0).getId();

        //when
        Optional<Board> result = boardRepository.findById(boardId);

        //then
        assertTrue(result.isPresent());
        assertEquals(boardsToSave.get(0).getId(), result.get().getId());
    }


    @Test
    @DisplayName("카테고리로_게시판_객체_찾기")
    @Transactional
    void findBoardByCategory() {
        //given
        BoardCategory category = BoardCategory.자유;

        //when
        Board result = boardRepository.findBoardByCategory(category);

        //then
        assertNotNull(result);
        assertEquals(boardsToSave.get(1).getCategory(), result.getCategory());
        assertEquals(boardsToSave.get(1).getId(), result.getId());
    }

    @Test
    @DisplayName("게시글id로_게시판_객체_찾기")
    @Transactional
    void findBoardByArticleListId() {
        //given
        Article article = Article.builder()
                .title("제목입니다.")
                .content("내용이구여")
                .password("pass")
                .board(boardsToSave.get(1))
                .build();
        Article article2 = Article.builder()
                .title("제목2")
                .content("내용2")
                .password("word")
                .board(boardsToSave.get(1)).build();
        articleRepository.save(article);
        articleRepository.save(article2);
        //when
        Board result = boardRepository.findBoardByArticleListId(article.getId());
        Board result2 = boardRepository.findBoardByArticleListId(article2.getId());

        //then
        assertNotNull(result);
        assertEquals(article.getBoard(), result);
        assertEquals(result, result2);
    }
}