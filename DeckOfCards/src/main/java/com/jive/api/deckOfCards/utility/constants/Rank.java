package com.jive.api.deckOfCards.utility.constants;


/**
 * @author Ashish.Patel
 *
 */
public enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(
			13);

	int priority;

	Rank(int s) {
		priority = s;
	}

	public int getPriority() {
		return priority;
	}

}
