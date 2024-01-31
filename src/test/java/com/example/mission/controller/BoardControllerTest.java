package com.example.mission.controller;

import com.example.mission.dto.AllArticleDto;
import com.example.mission.dto.BoardDto;
import com.example.mission.entity.Article;
import com.example.mission.entity.Board;
import com.example.mission.entity.BoardCategory;
import com.example.mission.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.DomainEvents;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class BoardControllerTest {

    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @Mock
    private Model mockModel;

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        // mockMvc 객체 초기화
        mockMvc = MockMvcBuilders
                .standaloneSetup(boardController)
                .alwaysDo(print())
                .build();
        MockitoAnnotations.initMocks(mockModel);
    }

    @Test
    @DisplayName("전체카테고리_불러오기")
    void showBoardList() throws Exception {
        // given
        List<BoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(BoardDto.fromEntity(Board.builder().category(BoardCategory.자유).build()));
        boardDtoList.add(BoardDto.fromEntity(Board.builder().category(BoardCategory.개발).build()));
        boardDtoList.add(BoardDto.fromEntity(Board.builder().category(BoardCategory.일상).build()));
        boardDtoList.add(BoardDto.fromEntity(Board.builder().category(BoardCategory.사건사고).build()));

        when(boardService.readBoardCategories()).thenReturn(boardDtoList);
        // when
        String viewName = boardController.showBoardList();

        // then
        assertEquals("boardList", viewName);
        verify(boardService, times(1)).readBoardCategories();
    }

    @Test
    @DisplayName("카테고리가_없을때_전체게시글")
    void readAllCategoryArticles() {
        // given
        Board board1 = Board.builder().category(BoardCategory.자유).build();

        List<AllArticleDto> articleDtoList = new ArrayList<>();
        articleDtoList.addAll(Collections.singleton(AllArticleDto.fromEntity(Article.builder().title("t1")
                .content("c1").password("p1").board(board1).build())));

        when(boardService.readAllArticles()).thenReturn(articleDtoList);

        // when
        String viewName = boardController.readAllCategoryArticles(mockModel);

        // then
        assertEquals("articleList", viewName);
        verify(boardService, times(1)).readAllArticles();
        verify(mockModel, times(1)).addAttribute(eq("articles"), eq(articleDtoList));
    }

    @Test
    @DisplayName("카테고리가_있을때_해당카테고리의_게시글")
    void readArticles() {
        //given
        Long boardId = 1L;
        Board board1 = Board.builder().category(BoardCategory.자유).build();
        BoardDto boardDto = BoardDto.fromEntity(board1);
        when(boardService.findByBoardId(boardId)).thenReturn(boardDto);

        List<AllArticleDto> articleDtoList = new ArrayList<>();
        articleDtoList.addAll(Collections.singleton(AllArticleDto.fromEntity(Article.builder().title("t1")
                .content("c1").password("p1").board(board1).build())));
        when(boardService.readAllArticlesByBoardId(boardId)).thenReturn(articleDtoList);

        // when
        String viewName = boardController.readArticles(boardId, mockModel);

        // then
        assertEquals("articleList", viewName);
        verify(boardService, times(1)).readAllArticlesByBoardId(boardId);
        verify(boardService, times(1)).findByBoardId(boardId);
        verify(mockModel, times(1)).addAttribute(eq("articles"), eq(articleDtoList));
        verify(mockModel, times(1)).addAttribute(eq("boards"), eq(boardDto));
    }

    @Test
    @DisplayName("게시글_검색하기")
    void searchArticles() {
        //given
        String criteria = "title";
        String searchString = "검색어";
        BoardCategory category = BoardCategory.자유;
        List<AllArticleDto> articleDtoList = new ArrayList<>();

        when(boardService.searchArticles(criteria, searchString, category)).thenReturn(articleDtoList);

        //when
        String viewName = boardController.searchArticles(criteria, searchString, category, mockModel);

        //then
        assertEquals("searchResult", viewName);
        verify(boardService, times(1)).searchArticles(criteria, searchString, category);
        verify(mockModel, times(1)).addAttribute(eq("articles"), eq(articleDtoList));
    }
}