package com.jive.api.deckOfCards.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ashish.Patel
 *
 */

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Game {

	private long gameId;
	private List<Card> cards;
	private List<Card> leftCards;
	private List<Player> playerList;
	private long deckId;
	
}