package com.jive.api.deckOfCards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jive.api.deckOfCards.dto.ResponseMessageDto;
import com.jive.api.deckOfCards.service.DeckOfCards;

/**
 * @author Ashish.Patel
 *
 */

@RestController
@RequestMapping("DeckofCards")
public class DeckOfCardsController {

	@Autowired
	DeckOfCards deckOfCards;
	
	
	public ResponseEntity<ResponseMessageDto> createGame(){
		
	}
	public ResponseEntity<ResponseMessageDto> deleteGame(long gameId){
		
	}
	
	public ResponseEntity<ResponseMessageDto> createDeck(long gameId){
		
	}
	
	public ResponseEntity<ResponseMessageDto> addPlayer(long gameId){
		
	}
	public ResponseEntity<ResponseMessageDto> removePlayer(long gameId,long playerId){
		
	}
	
	public ResponseEntity<ResponseMessageDto> dealCards(long gameId){
		
	}
	
	public ResponseEntity<ResponseMessageDto> getListofCardsForPlayer(long gameId,long playerId){
		
	}
	public ResponseEntity<ResponseMessageDto> getListofPlayer(long gameId){
		
	}
	
	public ResponseEntity<ResponseMessageDto> getCountOfLeftCards(long gameId){
		
	}
	public ResponseEntity<ResponseMessageDto> getCountOfEachCard(long gameId){
		
	}
	
	
	public ResponseEntity<ResponseMessageDto> shuffle(long gameId){
		
	}
	
}
