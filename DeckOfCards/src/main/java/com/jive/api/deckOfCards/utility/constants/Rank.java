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
	
	public int getPriorityByRank(String rank) {
		switch (rank) {
		case "ACE":
			return 1;
		case "TWO":
			return 2;
		case "THREE":
			return 3;
		case "FOUR":
			return 4;
		case "FIVE":
			return 5;
		case "SIX":
			return 6;
		case "SEVEN":
			return 7;
		case "EIGHT":
			return 8;
		case "NINE":
			return 9;
		case "TEN":
			return 10;
		case "JACK":
			return 11;
		case "QUEEN":
			return 12;
		case "KING":
			return 13;

		default:
			return 0;
		}

	}

	public Rank getRankByPriority(int p) {
		switch (p) {
		case 1:
			return Rank.ACE;
		case 2:
			return Rank.TWO;
		case 3:
			return Rank.THREE;
		case 4:
			return Rank.FOUR;
		case 5:
			return Rank.FIVE;
		case 6:
			return Rank.SIX;
		case 7:
			return Rank.SEVEN;
		case 8:
			return Rank.EIGHT;
		case 9:
			return Rank.NINE;
		case 10:
			return Rank.TEN;
		case 11:
			return Rank.JACK;
		case 12:
			return Rank.QUEEN;
		case 13:
			return Rank.KING;

		default:
			return null;
		}

	}
}
