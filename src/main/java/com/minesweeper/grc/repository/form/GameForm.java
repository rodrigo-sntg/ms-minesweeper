package com.minesweeper.grc.repository.form;

import com.minesweeper.grc.repository.entity.Board;
import com.minesweeper.grc.repository.entity.Game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameForm {

    private int rows;
    private int columns;
    private int bombs;
    private int difficulty;
    private String player;

    public Game convertToEntity() {

    	Game game =  new Game();
    	Board board = new Board();
    	board.setNumColumns(columns);
    	board.setNumRows(rows);

    	game.setBoard(board);
    	game.setDifficulty(difficulty);
    	game.setPlayer(player);

    	return game;


    }
}