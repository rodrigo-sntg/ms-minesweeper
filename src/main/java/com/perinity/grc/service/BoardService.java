package com.perinity.grc.service;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.perinity.grc.repository.BoardRepository;
import com.perinity.grc.repository.entity.Board;
import com.perinity.grc.repository.entity.Cell;
import com.perinity.grc.repository.entity.State;
import com.perinity.grc.utils.BoardUtils;

@ApplicationScoped
public class BoardService {

	@Inject
	BoardRepository repository;

	public void init(Board board){

		int numRows = board.getNumRows();
		int numColumns = board.getNumColumns();

    	Cell[][] cells = new Cell[board.getNumRows()][board.getNumColumns()];
    	Random r = new Random();

    	Set<Cell> bombs = new HashSet<>();

    	Set<Cell> cellList = new HashSet<>();
//    	Cell bomb1 = new Cell(1,1,(1*numColumns) + 1);
//    	bombs.add(bomb1);
    	for(int i = 0; i < board.getNumberOfBombs(); i++) {
    		Cell cell = generateRandomBomb(r, numRows, numColumns);
    		if (!bombs.contains(cell)) {
				bombs.add(cell);
			}else {
				while (!bombs.contains(cell)) {
		    		cell = generateRandomBomb(r, numRows, numColumns);
					bombs.add(cell);
				}
			}

    	}
        board.setBombs(new ArrayList<>(bombs));

    	for(int i = 0; i<numRows;i++){
            for(int j =0; j < numColumns; j++){
                int pos = (i*numColumns) + j;

            	Cell cell = new Cell(i,j,pos);
            	if(bombs.contains(cell)) {
            		Optional<Cell> findFirst = bombs.stream().filter(bomb -> bomb.equals(cell)).findFirst();
            		if(findFirst.isPresent()) {
            			cell.setBomb(true);
            			cell.setBombsAround(-1);

            		}

            	}

                cells[i][j] = cell;

				cellList.add(cell);
            }
        }
        board.setCells(cells);
        board.setCellList(new ArrayList<>(cellList));
    	System.out.println(bombs);
    	System.out.println();
    	BoardUtils.printCells2DSimple(cells);
    	System.out.println();
    	BoardUtils.printCells2D(cells);
    	System.out.println();

    	updateBombsCountersOnCells(board);

    	board.setCells(cells);
    	board.updateListFromMatrix();

    	board.setNumberOfRemainingCells(numRows*numColumns - board.getNumberOfBombs());

    }

	public void updateBombsCountersOnCells(Board board) {
		board.updateMatrixFromList();


    	for(Cell cell : board.getBombs()) {
    		for(int[] position : Board.POSITIONS) {
    			int row = cell.getRow() + position[0];
    			int col = cell.getCol() + position[1];
    			if(board.isValid(row,col)) {
    				Cell c = board.getCells()[row][col];
    				if(!c.isBomb())
    					c.incrementBombs();
    			}
    		}
    	}
    }

	private Cell generateRandomBomb(Random r, int i, int j) {
		int x = r.nextInt(i);
		int y = r.nextInt(j);

		Cell cell = new Cell(x,y,(x*j) + y);
		cell.setBomb(true);
		return cell;
	}

    public boolean checkCell(Cell cell, Board board){
    	if(!cell.isBomb() && !cell.isFlagged()){
            cell.flip();
            if(board.getNumberOfRemainingCells() == 0)
                return true;
            else
                board.setNumberOfRemainingCells(board.getNumberOfRemainingCells() - 1);
            return true;
        }
        return false;
    }


    public State clickCell(Cell cell, Board board){
        int r = cell.getRow();
        int column = cell.getCol();
		//cell = board.getCellList().stream().filter(item -> item.equals(new Cell(r,column,(r*board.getNumColumns()) + column))).findFirst().orElse(null);

        if((cell != null && !cell.isBlank() && checkCell(cell, board))){
            if(board.getNumberOfRemainingCells() == 0)
                return State.WON;
            return State.RUNNING;
        }
        else if(cell.isBomb()){
            cell.flip();
            return State.LOST;
        }

        dfsOpeningBlanks(cell, board);

        board.updateMatrixFromList();

        if(board.getNumberOfRemainingCells() == 0)
            return State.WON;
        return State.RUNNING;

    }

	private void dfsOpeningBlanks(Cell cell, Board board) {
		Deque<Cell> dq = new LinkedList<>();
        dq.offer(cell);
        board.updateMatrixFromList();
        while(!dq.isEmpty()) {
        	Cell localCell = dq.poll();

        	for(int[] position : Board.POSITIONS) {
    			int row = localCell.getRow() + position[0];
    			int col = localCell.getCol() + position[1];
    			if(board.isValid(row,col)) {

    				Cell c = board.getCellList().stream().filter(item -> item.equals(new Cell(row,col,(row*board.getNumColumns()) + col))).findFirst().orElse(null);
    				if(c != null && c.isBlank() && checkCell(c, board) ){
                        dq.add(c);

                    }
    			}
    		}
        }
	}

}
