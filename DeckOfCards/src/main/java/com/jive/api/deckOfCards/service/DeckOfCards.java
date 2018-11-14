package com.jive.api.deckOfCards.service;

import com.jive.api.deckOfCards.dto.ResponseMessageDto;


/**
 * @author Ashish.Patel
 *
 */

public interface DeckOfCards {

	public ResponseMessageDto createGame();
	public ResponseMessageDto deleteGame(long gameId);
	
	public ResponseMessageDto createDeck(long gameId);
	
	public ResponseMessageDto addPlayer(long gameId);
	public ResponseMessageDto removePlayer(long gameId,long playerId);
	
	public ResponseMessageDto dealCards(long gameId);
	
	public ResponseMessageDto getListofCardsForPlayer(long gameId,long playerId);
	public ResponseMessageDto getListofPlayer(long gameId);
	
	public ResponseMessageDto getCountOfLeftCards(long gameId);
	public ResponseMessageDto getCountOfEachCard(long gameId);
	
	
	public ResponseMessageDto shuffle(long gameId);
	
	
}
