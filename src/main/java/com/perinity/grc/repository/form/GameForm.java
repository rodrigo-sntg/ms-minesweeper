package com.perinity.grc.repository.form;

import org.apache.commons.lang3.RandomStringUtils;

import com.perinity.grc.repository.entity.Board;
import com.perinity.grc.repository.entity.Game;

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