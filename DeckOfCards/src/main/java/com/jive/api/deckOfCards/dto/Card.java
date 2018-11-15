package com.jive.api.deckOfCards.dto;

import java.io.Serializable;

import com.jive.api.deckOfCards.utility.constants.Rank;
import com.jive.api.deckOfCards.utility.constants.Suit;

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
public class  Card implements Serializable {
    
	private static final long serialVersionUID = 5620453328735961277L;
	public Suit suit;
    public Rank rank;

}
