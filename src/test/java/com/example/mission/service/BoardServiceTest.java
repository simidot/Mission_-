package com.example.mission.service;

import com.example.mission.dto.AllArticleDto;
import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.repo.ArticleRepository;
import com.example.mission.repo.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private BoardService boardService;

    private List<Board> boards;

    @BeforeEach
    @Transactional
    void createDefaultBoards() {
        boards = new ArrayList<>();
        Board board1 = new Board(BoardCategory.자유);
        Board board2 = Board.builder().category(BoardCategory.개발).build();
        Board board3 = Board.builder().category(BoardCategory.일상).build();
        Board board4 = Board.builder().category(BoardCategory.사건사고).build();
        boards.addAll(Arrays.asList(board1, board2, board3, board4));
    }

    // 전체 게시판 카테고리 불러오기
    @Test
    @DisplayName("전체_게시판_카테고리_불러오기")
    @Transactional
    void readBoardCategories() {
        // given
        // 1. BoardCategory 를 repository로 전달받을 것을 가정한다.
        //BeforeEach

        // 2. boardRepository.findAll()의 결과를 Board 객체 리스트로 반환한다고 설정.
        when(boardRepository.findAll()).thenReturn(boards);

        // when
        // 3. Board 객체 리스트를 BoardDto리스트로 전달
        List<BoardDto> dtoList = boardService.readBoardCategories();

        // then
        assertEquals(4, dtoList.size());
        assertEquals(dtoList.get(0).getCategory(), boards.get(0).getCategory().toString());
        assertEquals(dtoList.get(1).getCategory(), boards.get(1).getCategory().toString());
        assertEquals(dtoList.get(2).getCategory(), boards.get(2).getCategory().toString());
        assertEquals(dtoList.get(3).getCategory(), boards.get(3).getCategory().toString());
    }

    @Test
    @DisplayName("게시판아이디로_게시판 카테고리 불러오기")
    @Transactional
    void findByBoardId() {
        // given
        Long boardId = 1L;

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(boards.get(0)));

        // when
        BoardDto result = boardService.findByBoardId(boardId);

        // then
        assertEquals(result.getCategory(), BoardCategory.자유.toString());
    }

    @Test
    @DisplayName("전체_카테고리_게시물_불러오기")
    void readAllArticles() {
        // given
        Article article1 = Article.builder().title("article1")
                .content("content1")
                .password("password1")
                .board(boards.get(0)).build();
        Article article2 = Article.builder().title("article2")
                .content("content2")
                .password("password2")
                .board(boards.get(0)).build();
        Article article3 = Article.builder().title("article3")
                .content("content3")
                .password("password3")
                .board(boards.get(1)).build();
        when(articleRepository.findAllByOrderByCreateDateDesc()).thenReturn(List.of(article1, article2, article3));

        // when
        List<AllArticleDto> result = boardService.readAllArticles();

        // then
        assertEquals(3, result.size());
        assertEquals("content1" ,result.get(0).getContent());
        assertEquals("password2", result.get(1).getPassword());
        assertEquals("article3", result.get(2).getTitle());
    }

    @Test
    @DisplayName("카테고리별_게시물_전체_불러오기")
    void readAllArticlesByBoardId() {
        // given
        Long boardId = 1L;
        Article article1 = Article.builder().title("article1")
                .content("content1")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("article2")
                .content("content2")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("article3")
                .content("content3")
                .password("password3")
                .board(boards.get(1)).build(); // 개발
        when(articleRepository.findAllByBoardIdOrderByCreateDateDesc(boardId)).thenReturn(List.of(article1, article2));

        // when
        List<AllArticleDto> result = boardService.readAllArticlesByBoardId(boardId);

        // then
        assertEquals(2, result.size());
        assertEquals("content1" ,result.get(0).getContent());
        assertEquals("password2", result.get(1).getPassword());
    }

    @Test
    @DisplayName("게시글아이디로_게시판객체_찾기")
    void findBoardByArticleId() {
        // given
        Long articleId = 1L;
        Article article1 = Article.builder().title("article1")
                .content("content1")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("article2") // 1L이라고하자.
                .content("content2")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("article3")
                .content("content3")
                .password("password3")
                .board(boards.get(1)).build(); // 개발
        when(boardRepository.findBoardByArticleListId(articleId)).thenReturn(article2.getBoard());

        // when
        BoardDto result = boardService.findBoardByArticleId(articleId);

        // then
        assertEquals(BoardCategory.자유.toString(), result.getCategory());
    }

    @Test
    @DisplayName("1_카테고리_자유_기준_제목_검색")
    void searchArticles() {
        // given
        BoardCategory category = BoardCategory.자유;
        String criteria = "title";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByTitleContainingAndBoard_Category(searchString, category)).thenReturn(List.of(article1));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(1, result.size());
        assertEquals("검색어포함", result.get(0).getTitle());
    }

    @Test
    @DisplayName("2_카테고리_자유_기준_내용_검색")
    void searchArticles2() {
        // given
        BoardCategory category = BoardCategory.자유;
        String criteria = "content";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByContentContainingAndBoard_Category(searchString, category)).thenReturn(List.of(article2));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(1, result.size());
        assertEquals("미포함", result.get(0).getTitle());
        assertEquals("검색어 포함이용", result.get(0).getContent());
    }

    @Test
    @DisplayName("3_카테고리_자유_기준_없음_검색")
    void searchArticles3() {
        // given
        BoardCategory category = BoardCategory.자유;
        String criteria = "";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByTitleContainingOrContentContainingAndBoard_Category(searchString,searchString, category)).thenReturn(List.of(article1, article2));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(2, result.size());
        assertEquals("미포함", result.get(1).getTitle());
        assertEquals("검색어 포함이용", result.get(1).getContent());
        assertEquals("검색어포함", result.get(0).getTitle());
    }

    @Test
    @DisplayName("4_카테고리_없음_기준_제목_검색")
    void searchArticles4() {
        // given
        BoardCategory category = null;
        String criteria = "title";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByTitleContaining(searchString)).thenReturn(List.of(article1, article3));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(2, result.size());
        assertEquals("검색어포함", result.get(0).getTitle());
        assertEquals("미포함", result.get(1).getContent());
        assertEquals("검색어포함", result.get(1).getTitle());
    }

    @Test
    @DisplayName("5_카테고리_없음_기준_내용_검색")
    void searchArticles5() {
        // given
        BoardCategory category = null;
        String criteria = "content";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByContentContaining(searchString)).thenReturn(List.of(article2));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(1, result.size());
        assertEquals("미포함", result.get(0).getTitle());
        assertEquals("검색어 포함이용", result.get(0).getContent());
    }

    @Test
    @DisplayName("6_카테고리_없음_기준_없음_검색")
    void searchArticles6() {
        // given
        BoardCategory category = null;
        String criteria = "";
        String searchString = "검색어";

        Article article1 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password1")
                .board(boards.get(0)).build(); //자유
        Article article2 = Article.builder().title("미포함")
                .content("검색어 포함이용")
                .password("password2")
                .board(boards.get(0)).build(); // 자유
        Article article3 = Article.builder().title("검색어포함")
                .content("미포함")
                .password("password3")
                .board(boards.get(1)).build(); // 개발

        when(articleRepository.findAllByTitleContainingOrContentContaining(searchString, searchString)).thenReturn(List.of(article1, article2, article3));

        // when
        List<AllArticleDto> result = boardService.searchArticles(criteria, searchString, category);
        System.out.println(result.toString());

        // then
        assertEquals(3, result.size());
        assertEquals("검색어포함", result.get(0).getTitle());
        assertEquals("미포함", result.get(1).getTitle());
        assertEquals("검색어 포함이용", result.get(1).getContent());
        assertEquals("검색어포함", result.get(2).getTitle());

    }
}
