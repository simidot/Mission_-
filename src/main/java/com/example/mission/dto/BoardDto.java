package com.example.mission.dto;

import com.example.mission.entity.Board;
import lombok.Data;

@Data
public class BoardDto {
    private Long id;
    private String category;

    public BoardDto(Board board) {
        this.id = board.getId();
        this.category = String.valueOf(board.getCategory());
    }
}
