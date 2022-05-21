package com.perinity.grc.repository.dto;

import com.perinity.grc.repository.entity.Cell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
