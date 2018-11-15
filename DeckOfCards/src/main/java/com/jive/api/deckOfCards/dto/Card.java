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
    /**
	 * 
	 */
	private static final long serialVersionUID = 5620453328735961277L;
	public Suit suit;
    public Rank rank;
//    public  enum  Suit{
//        Spade , Heart , Diamond , Clubs
//    }
    
    

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
//	@Override
//	public String toString() {
//		return "Card [s=" + s + ", r=" + r + " "+r.getPriority()+"]";
//	}


//	public enum Rank{
//        ACE(1) , TWO(2), THREE(3), FOUR(4), FIVE(5) , SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN (10), JACK(11), QUEEN (12), KING(13);
//
//        int priority;
//        Rank(int s) {
//            priority = s;
//        }
//
//        public int getPriority(){
//            return priority;
//        }
//
//        public Rank getRankByPriority(int p){
//            switch (p){
//                case 1: return Rank.ACE;
//                case 2: return Rank.TWO;
//                case 3: return Rank.THREE;
//                case 4: return Rank.FOUR;
//                case 5: return Rank.FIVE;
//                case 6: return Rank.SIX;
//                case 7: return Rank.SEVEN;
//                case 8: return Rank.EIGHT;
//                case 9: return Rank.NINE;
//                case 10: return Rank.TEN;
//                case 11: return Rank.JACK;
//                case 12: return Rank.QUEEN;
//                case 13: return Rank.KING;
//
//                default: return null;
//            }
//
//        }
//    }

//    public Rank getRank(){
//        return r;
//    }
//
//    public Suit getSuit(){
//        return s;
//    }


//    public Card(Rank r, Suit s){
//        this.rank = r;
//        this.suit = s;
//    }
//
//
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "Card [suit=" + suit + ", rank=" + rank + "]";
//	}


	

}
