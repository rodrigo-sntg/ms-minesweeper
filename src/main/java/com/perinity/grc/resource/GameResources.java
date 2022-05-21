package com.perinity.grc.resource;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.perinity.grc.repository.dto.GameDTO;
import com.perinity.grc.repository.entity.Cell;
import com.perinity.grc.repository.entity.State;
import com.perinity.grc.repository.entity.UserPlay;
import com.perinity.grc.repository.form.PlayForm;
import com.perinity.grc.service.BoardService;
import com.perinity.grc.service.UserPlayService;
import com.perinity.grc.utils.BoardUtils;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.opentracing.Traced;

import com.perinity.grc.repository.form.GameForm;

import com.perinity.grc.repository.entity.Game;

import com.perinity.grc.service.GameService;

@Path("/game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResources {

	private final GameService service;
    private final BoardService boardService;
    private final UserPlayService userPlayService;

	public GameResources(GameService service, BoardService boardService, UserPlayService userPlayService) {
        this.userPlayService = userPlayService;
        this.service = service;
        this.boardService = boardService;
	}

	@POST
    @Transactional
	public GameDTO startGame(@RequestBody GameForm gameForm) {
		Game game = service.startGame(gameForm.convertToEntity());
        GameDTO gameDTO = new GameDTO(game);
        return gameDTO;

	}

    @POST
    @Path("play")
    @Transactional
    public GameDTO play(@RequestBody PlayForm playForm) {
        GameDTO gameDto = service.play(playForm);
        return gameDto;
    }

    @GET
    @Path("{id}")
    public GameDTO getGameById(@PathParam("id") long id){
        Game game = service.findGameById(id);
        game.getBoard().updateMatrixFromList();
        GameDTO gameDTO = new GameDTO(game);
        return gameDTO;
    }

    @GET
    @Path("{code}/code")
    public GameDTO getGameById(@PathParam("code") String code){
        Game game = service.findByCode(code);
        game.getBoard().updateMatrixFromList();
        GameDTO gameDTO = new GameDTO(game);
        return gameDTO;
    }

	@DELETE
	@Path("/{code}")
	@Transactional
	@Traced
	public boolean remover(@PathParam("code") String code) {
        boolean deleted = service.deleteByCode(code);

        return deleted;

	}

}
