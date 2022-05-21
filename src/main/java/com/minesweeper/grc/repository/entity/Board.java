package com.minesweeper.grc.repository.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

import com.minesweeper.grc.utils.BoardUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

	public static final int[][] POSITIONS = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1}, {1,0},{1,1}};


    @Id
    @SequenceGenerator(name = "boardseq", sequenceName = "board_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "boardseq")
	private Long id;

    private int numRows;
    private int numColumns;
    private int bombsNumber;


    @Transient
    private Cell[][] cells;
    @Cascade(CascadeType.ALL)
    @OneToMany(orphanRemoval = true)
    @JoinTable(
        name="rel_board_cells",
        joinColumns = @JoinColumn( name="board_id"),
        inverseJoinColumns = @JoinColumn( name="cell_id")
    )
    private List<Cell> cellList = new ArrayList<Cell>();

    @Cascade(CascadeType.ALL)
    @OneToMany(orphanRemoval = true)
    @JoinTable(
        name="rel_board_bombs",
        joinColumns = @JoinColumn( name="board_id"),
        inverseJoinColumns = @JoinColumn( name="cell_id")
    )
    private List<Cell> bombs  = new ArrayList<Cell>();
    private int numberOfBombs;
    private int difficulty;
    private int numberOfRemainingCells;


    Board(Integer numberOfRows, Integer numberOfColumns, Integer difficulty, Integer numberOfBombs){
    	this.numRows = numberOfRows;
    	this.numColumns = numberOfColumns;
    	this.difficulty = difficulty;
    }

    Board(List<Cell> cellList){
    	this.cellList = cellList;
    	this.updateMatrixFromList();
    }



    public int getSize() {
    	return this.numColumns * this.numRows;
    }

    public void updateMatrixFromList(){
        Cell[][] cells = new Cell[numRows][numColumns];
        Map<Integer, Cell> mapCells = new HashMap<Integer, Cell>();
        for(Cell cell: cellList)
            mapCells.put(cell.getListPosition(), cell);

        for(int i = 0; i<numRows;i++){
            for(int j =0; j < numColumns; j++){
                cells[i][j] = mapCells.get((i*numColumns) + j);
            }
        }
        this.cells = cells;
    }

    public void updateListFromMatrix() {
    	this.getCellList().clear();
    	for(int i = 0; i<cells.length;i++){
            for(int j =0; j < cells[0].length; j++){
                int pos = (i*cells[0].length) + j;
                Cell cell = cells[i][j];
                cell.setListPosition(pos);
                this.getCellList().add(cell);
            }
        }
    }

    public boolean isValid(int x, int y) {
		return x >= 0 && x < cells.length && y >= 0 && y < cells[0].length && !cells[x][y].isOpened();

    }

    public static void main(String[] args) {
//        int[][] matrixDFS = {	{1,2,3,2,2},
//								{3,2,3,2,2},
//								{3,3,3,3,3},
//								{3,2,3,2,1},
//								{1,2,3,2,2}};

        int[][] matrixDFS = new int[6][6];

        Board board = new Board(matrixDFS.length, matrixDFS[0].length, Game.DIFFICULTY_EASY, null);

        int i = 1;
        int j = 1;
        Cell cell = new Cell(i,j,(i*board.getNumColumns()) + j);
        BoardUtils.printCells2D(board.getCells());
//        board.clickCell(cell);
        BoardUtils.printSurface(board.getCells());

    }

}