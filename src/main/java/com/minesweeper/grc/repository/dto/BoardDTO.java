package com.minesweeper.grc.repository.dto;

import com.minesweeper.grc.repository.entity.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {

    private Long id;
    private int numRows;
    private int numColumns;
    private CellDTO[][] cells;
    private int numberOfRemainingCells;

    BoardDTO(Board board, boolean isEndGame){
        this.setId(board.getId());
        this.setNumRows(board.getNumRows());
        this.setNumColumns(board.getNumColumns());
        this.setNumberOfRemainingCells(board.getNumberOfRemainingCells());
        board.updateMatrixFromList();
        cells = new CellDTO[board.getNumRows()][board.getNumColumns()];
        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                CellDTO cellDTO = new CellDTO(board.getCells()[i][j], isEndGame);
                cells[i][j] = cellDTO;
            }
        }
    }

}
