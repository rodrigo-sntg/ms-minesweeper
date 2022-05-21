package com.perinity.grc.repository.dto;

import com.perinity.grc.repository.entity.Board;
import com.perinity.grc.repository.entity.Cell;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardDTO {

    private Long id;
    private int numRows;
    private int numColumns;
    private CellDTO[][] cells;
    private int numberOfRemainingCells;

    BoardDTO(Board board){
        this.setId(board.getId());
        this.setNumRows(board.getNumRows());
        this.setNumColumns(board.getNumColumns());
        this.setNumberOfRemainingCells(board.getNumberOfRemainingCells());
        board.updateMatrixFromList();
        cells = new CellDTO[board.getNumRows()][board.getNumColumns()];
        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumColumns(); j++) {
                CellDTO cellDTO = new CellDTO(board.getCells()[i][j]);
                cells[i][j] = cellDTO;
            }
        }
    }

}
