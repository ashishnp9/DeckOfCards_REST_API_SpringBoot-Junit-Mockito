package com.jive.api.deckOfCards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jive.api.deckOfCards.dto.ResponseMessageDto;
import com.jive.api.deckOfCards.service.DeckOfCards;

/**
 * @author Ashish.Patel
 *
 */

@RestController
@RequestMapping("/DeckofCards")
public class DeckOfCardsController {

	@Autowired
	DeckOfCards deckOfCards;

	@PostMapping("/createGame")
	public ResponseEntity<ResponseMessageDto> createGame() {
		return new ResponseEntity<ResponseMessageDto>(deckOfCards.createGame(), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteGame")
	public ResponseEntity<ResponseMessageDto> deleteGame(@PathVariable("gameId") final String gameId) {

		ResponseMessageDto dto = deckOfCards.deleteGame(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}

	}

	@PostMapping("/createDeck")
	public ResponseEntity<ResponseMessageDto> createDeck(@PathVariable("gameId") final String gameId) {
		return new ResponseEntity<ResponseMessageDto>(deckOfCards.createDeck(Long.valueOf(gameId)), HttpStatus.CREATED);
	}

	@PostMapping("/addPlayer")
	public ResponseEntity<ResponseMessageDto> addPlayer(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.addPlayer(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.CREATED);
		}
	}

	@DeleteMapping("/removePlayer")
	public ResponseEntity<ResponseMessageDto> removePlayer(@PathVariable("gameId") final String gameId,
			@PathVariable("playerId") final String playerId) {
		ResponseMessageDto dto = deckOfCards.removePlayer(Long.valueOf(gameId), Long.valueOf(playerId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@PostMapping("/dealCards")
	public ResponseEntity<ResponseMessageDto> dealCards(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.dealCards(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getListofCardsForPlayer")
	public ResponseEntity<ResponseMessageDto> getListofCardsForPlayer(@PathVariable("gameId") final String gameId,
			@PathVariable("playerId") final String playerId) {
		ResponseMessageDto dto = deckOfCards.getListofCardsForPlayer(Long.valueOf(gameId), Long.valueOf(playerId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getListofPlayer")
	public ResponseEntity<ResponseMessageDto> getListofPlayer(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getListofPlayer(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getCountOfLeftCards")
	public ResponseEntity<ResponseMessageDto> getCountOfLeftCards(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getCountOfLeftCards(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/getCountOfEachCard")
	public ResponseEntity<ResponseMessageDto> getCountOfEachCard(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.getCountOfEachCard(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

	@GetMapping("/shuffle")
	public ResponseEntity<ResponseMessageDto> shuffle(@PathVariable("gameId") final String gameId) {
		ResponseMessageDto dto = deckOfCards.shuffle(Long.valueOf(gameId));
		if (dto.isError()) {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<ResponseMessageDto>(dto, HttpStatus.OK);
		}
	}

}
