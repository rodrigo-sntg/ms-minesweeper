package com.perinity.grc.repository.dto;

import com.perinity.grc.repository.entity.Board;

import com.perinity.grc.repository.entity.Game;
import com.perinity.grc.repository.entity.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameDTO {

	private BoardDTO board;
    private int difficulty;
    private State state;
    private String player;
    private String code;

    public GameDTO(Game game){
        this.setDifficulty(game.getDifficulty());
        this.setCode(game.getCode());
        this.setState(game.getState());
        this.setPlayer(game.getPlayer());
        this.setBoard(new BoardDTO(game.getBoard()));
    }

}