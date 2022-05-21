package com.minesweeper.grc.repository.dto;

import com.minesweeper.grc.repository.entity.Cell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CellDTO {

	private Long id;
    private int row;
    private int col;

    private String surfaceValue;

    @Override
    public String toString() {

        return "["+row +","+ col +"]";
    }

    public CellDTO(Cell cell){
        this.setId(cell.getId());
        this.setRow(cell.getRow());
        this.setCol(cell.getCol());
        this.setSurfaceValue(cell.getSurfaceValue());
    }
}
