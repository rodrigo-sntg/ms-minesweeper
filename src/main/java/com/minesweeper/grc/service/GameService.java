package com.minesweeper.grc.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.transaction.Transactional;

import com.minesweeper.grc.repository.GameRepository;
import com.minesweeper.grc.repository.dto.GameDTO;
import com.minesweeper.grc.repository.entity.Cell;
import com.minesweeper.grc.repository.entity.Game;
import com.minesweeper.grc.repository.entity.State;
import com.minesweeper.grc.repository.entity.UserPlay;
import com.minesweeper.grc.repository.form.PlayForm;
import com.minesweeper.grc.utils.BoardUtils;
import com.minesweeper.grc.utils.GameUtils;
import com.minesweeper.grc.exceptions.CustomException;
import org.apache.commons.lang3.RandomStringUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ApplicationScoped
public class GameService {

	@Inject
    GameRepository repository;

	@Inject
	BoardService boardService;

    @ConfigProperty(name = "knowledgefactory.custom.error.msg.gameNotFound")
    String gameNotFound;
    private UserPlayService userPlayService;

    public GameService(UserPlayService userPlayService) {
        this.userPlayService = userPlayService;
    }

//	public ObjetoDto inserir(@Valid Game game) {
//		// TODO Auto-generated method stub
//		return null;
//	}

    @Transactional
	public Game startGame(Game game) {

		game.setCode(generateCode());
        game.setState(State.RUNNING);
		game.getBoard().setNumberOfBombs(GameUtils.getNumberOfBombs(game.getDifficulty(), game.getBoard().getSize(), null));

		boardService.init(game.getBoard());
        game.getStartingTimeList().add(System.currentTimeMillis());

		repository.persistAndFlush(game);
        game.getBoard().updateMatrixFromList();
        BoardUtils.printCells2DSimple(game.getBoard().getCells());

        System.out.println();

        BoardUtils.printSurface(game.getBoard().getCells());

		return game;
	}

    public Game saveChanges(Game game){
        this.repository.persistAndFlush(game);
        return game;
    }

    public GameDTO play(PlayForm playForm){
        Game game = this.findByCode(playForm.getBoardCode());

        Cell playedCelll = BoardUtils.getCellFromCoords(playForm.getRow(), playForm.getCol(), game.getBoard());

        if(playedCelll.isOpened()){
            GameDTO gameDTO = new GameDTO(game);
            return gameDTO;
        }

        UserPlay userPlay = new UserPlay();
        userPlay.setGame(game);
        userPlay.setCell(playedCelll);
        userPlay.setStateBeforePlay(game.getState());
        userPlay.setPlayNumber(game.getPlaysList().size() + 1);

        State state = boardService.clickCell(playedCelll, game.getBoard());
        game.setState(state);
        userPlay.setStateAfterPlay(state);
        userPlay = userPlayService.inserir(userPlay);
        game.getPlaysList().add(userPlay);

        if(state == State.LOST) {
            game.getEndingTimeList().add(System.currentTimeMillis());
        }
        this.saveChanges(game);

        game.getBoard().updateMatrixFromList();
        BoardUtils.printCells2DSimple(game.getBoard().getCells());

        System.out.println();

        BoardUtils.printSurface(game.getBoard().getCells());

        GameDTO gameDTO = new GameDTO(game);
        return gameDTO;
    }


	public static String generateCode() {
		int length = 6;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
	}

	public static void main(String[] args) {
		System.out.println(generateCode());
	}

    public Game findGameById(long id) {

        Optional<Game> game = this.repository.findByIdOptional(id);
        if(game.isPresent())
            return game.get();
        else
            throw new CustomException(gameNotFound);
    }

    public Game findByCode(String code) {
        Optional<Game> game = this.repository.findByCode(code);
        if(game.isPresent())
            return game.get();
        else
            throw new CustomException(gameNotFound);
    }

    public boolean deleteByCode(String code) {
        Optional<Game> game = this.repository.findByCode(code);
        if(game.isPresent()){
            this.repository.delete(game.get());
        }
        else
            throw new CustomException(gameNotFound);

        return true;
    }
}
