package com.jive.api.deckOfCards.dto;

import java.io.Serializable;
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
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1203823022136645166L;
	private long gameId;
	private List<Card> cards;
	private List<Card> leftCards;
	private List<Player> playerList;
	private long deckId;
	
}