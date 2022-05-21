package com.minesweeper.grc.repository.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cell {

    @Id
    @SequenceGenerator(name = "cellseq", sequenceName = "cell_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "cellseq")
	private Long id;
    private int row;
    private int col;
    private boolean isBomb  = false;
    private int bombsAround;
    private boolean isOpened = false;
    private boolean isFlagged  = false;
    private int listPosition; // (Número da Linha * Numero de Colunas da matriz) + Número da Coluna

    public Cell(int i, int j, int position){
        this.row = i;
        this.col = j;
        this.listPosition = position;
    }

    public void toggleGuess(){

        if(!isOpened)
            this.isFlagged = !this.isFlagged;
    }

    @Override
    public String toString() {

        return "["+row +","+ col +"]";
    }

    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	if(obj instanceof Cell) {
    		Cell cell = (Cell) obj;
    		return (cell.getRow() == this.getRow() && cell.getCol() == this.getCol());
    	}
    	return super.equals(obj);
    }

    @Override
    public int hashCode() {
    	// TODO Auto-generated method stub
    	return this.getRow() * this.getCol() * this.listPosition;
    }

    public void setRowAndColumn(int i, int j){
        this.row = i;
        this.col = j;
    }

    public void incrementBombs() {
    	this.bombsAround++;
    }

    public boolean flip() {
    	this.isOpened = true;
    	return !isBomb;
    }

    public boolean isBlank() {
    	return this.getBombsAround() == 0;
    }

    public String getSurfaceValue() {
    	String val = "";
    	if(isOpened ) {
    		if(!isBomb)
    			return this.bombsAround + "";
    		else return "*";
    	}
        if(isFlagged)
            return "F";
    	return "?";
    }
}
