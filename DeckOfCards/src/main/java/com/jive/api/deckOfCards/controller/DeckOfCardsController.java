package com.jive.api.deckOfCards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jive.api.deckOfCards.dto.Game;
import com.jive.api.deckOfCards.dto.ResponseMessageDto;
import com.jive.api.deckOfCards.service.DeckOfCards;

/**
 * @author Ashish.Patel
 *
 */

@RestController
@RequestMapping("/deckofCards")
public class DeckOfCardsController {

	@Autowired
	DeckOfCards deckOfCards;

	@PostMapping("/createGame")
	public ResponseEntity<ResponseMessageDto> createGame() {
		return new ResponseEntity<ResponseMessageDto>(deckOfCards.createGame(), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteGame/{gameId}")
	public ResponseEntity<ResponseMessageDto> deleteGame(@PathVariable("gameId") final String gameId) {

		ResponseMessageDto dto = deckOfCards.deleteGame(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}

	}

	@PostMapping("/createDeck")
	public ResponseEntity<ResponseMessageDto> createDeck(@RequestBody Game game) {
		return new ResponseEntity<ResponseMessageDto>(deckOfCards.createDeck(game.getGameId()), HttpStatus.CREATED);
	}

	@PostMapping("/addPlayer")
	public ResponseEntity<ResponseMessageDto> addPlayer(@RequestBody Game game) {
		ResponseMessageDto dto = deckOfCards.addPlayer(game.getGameId());
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/removePlayer/{gameId}/{playerId}")
	public ResponseEntity<ResponseMessageDto> removePlayer(@PathVariable("gameId") final String gameId,
			@PathVariable("playerId") final String playerId) {
		ResponseMessageDto dto = deckOfCards.removePlayer(Long.valueOf(gameId), Long.valueOf(playerId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/dealCards/{gameId}")
	public ResponseEntity<ResponseMessageDto> dealCards(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.dealCards(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getListofCardsForPlayer/{gameId}/{playerId}")
	public ResponseEntity<ResponseMessageDto> getListofCardsForPlayer(@PathVariable("gameId") final String gameId,
			@PathVariable("playerId") final String playerId) {
		ResponseMessageDto dto = deckOfCards.getListofCardsForPlayer(Long.valueOf(gameId), Long.valueOf(playerId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getListofPlayer/{gameId}")
	public ResponseEntity<ResponseMessageDto> getListofPlayer(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getListofPlayer(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getCountOfLeftCards/{gameId}")
	public ResponseEntity<ResponseMessageDto> getCountOfLeftCards(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getCountOfLeftCards(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getCountOfEachCard/{gameId}")
	public ResponseEntity<ResponseMessageDto> getCountOfEachCard(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getCountOfEachCard(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/shuffle/{gameId}")
	public ResponseEntity<ResponseMessageDto> shuffle(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.shuffle(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

}
