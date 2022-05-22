package com.minesweeper.grc.utils;

import com.minesweeper.grc.repository.entity.Board;
import com.minesweeper.grc.repository.entity.Cell;

public class BoardUtils {
    public static void print2D(int matrix[][])
    {
		for (int i = 0; i < matrix.length; i++) {
	         for (int j = 0; j < matrix[i].length; j++) {
	            System.out.print(matrix[i][j] + " ");
	         }
	         System.out.println();
	      }
    }

    public static void printCells2DDetailed(Cell matrix[][])
    {
		for (int i = 0; i < matrix.length; i++) {
	         for (int j = 0; j < matrix[i].length; j++) {
	            System.out.print(matrix[i][j] + " isBomb=" + matrix[i][j].isBomb() + " bombsAround=" + matrix[i][j].getBombsAround());
	         }
	         System.out.println();
	      }
    }

    public static void printCells2D(Cell matrix[][])
    {
		for (int i = 0; i < matrix.length; i++) {
	         for (int j = 0; j < matrix[i].length; j++) {
	        	 if(matrix[i][j].getBombsAround() == -1)
	        		 System.out.print(matrix[i][j].getBombsAround() + "   " );
	        	 else
	        		 System.out.print(matrix[i][j].getBombsAround() + "    ");
//        		 System.out.print(matrix[i][j] + " ");

	         }
	         System.out.println();
	      }
    }

    public static void printCells2DSimple(Cell matrix[][])
    {
		for (int i = 0; i < matrix.length; i++) {
	         for (int j = 0; j < matrix[i].length; j++) {
        		 System.out.print(matrix[i][j] + " ");

	         }
	         System.out.println();
	      }
    }

    public static void printSurface(Cell matrix[][])
    {
		for (int i = 0; i < matrix.length; i++) {
	         for (int j = 0; j < matrix[i].length; j++) {
        		 System.out.print(matrix[i][j].getSurfaceValue(false) + " ");

	         }
	         System.out.println();
	      }
    }

    public static Cell getCellFromCoords(int row, int col, Board board){
        Cell cell = board.getCellList().stream().filter(c -> c.getRow() == row && c.getCol() == col).findFirst().orElse(null);

        return cell;
    }
}
