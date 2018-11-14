package com.jive.api.deckOfCards.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jive.api.deckOfCards.dto.Card;
import com.jive.api.deckOfCards.dto.Game;
import com.jive.api.deckOfCards.dto.Player;
import com.jive.api.deckOfCards.dto.ResponseMessageDto;


/**
 * @author Ashish.Patel
 *
 */

@Service
public class DeckOfCardsImpl implements DeckOfCards {

	private static Map<Long, Game> gameMap;
	private static AtomicLong gameIdCounter = new AtomicLong();
	private static AtomicLong playerIdCounter = new AtomicLong();
	private static AtomicLong deckIdCounter = new AtomicLong();

	static {
		gameMap = new HashMap<>();
	}

	@Override
	public ResponseMessageDto createGame() {
		ResponseMessageDto dto = new ResponseMessageDto();
		long gameId = gameIdCounter.getAndIncrement() + 1;

		List<Card> cards = new ArrayList<>();

		for (Card.Suit s : Card.Suit.values()) {
			for (Card.Rank r : Card.Rank.values()) {
				cards.add(new Card(r, s));
			}
		}

		gameMap.put(gameId, new Game(gameId, cards, new ArrayList<Card>(), new ArrayList<Player>(), 0l));
		dto.setResponseMessage("Your game Has been created Here Is your Game ID : " + gameId);
		dto.setResponseCode(HttpStatus.CREATED.value());
		dto.setError(false);
		// System.out.println("before " + gameMap);
		return dto;

	}

	@Override
	public ResponseMessageDto deleteGame(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();

		if (gameMap.containsKey(gameId)) {

			gameMap.remove(gameId);
			dto.setResponseMessage("Game Deleted");
			dto.setResponseCode(HttpStatus.OK.value());
			dto.setError(false);

		} else {
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setResponseMessage("Please provide valid GameId");
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}

		return dto;
	}

	@Override
	public ResponseMessageDto createDeck(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {
			long deckId = deckIdCounter.getAndIncrement() + 1;
			gameMap.get(gameId).setDeckId(deckId);
			dto.setResponseMessage("Your deck id Has been created Here Is your deck ID : " + deckId);
			dto.setError(false);
			dto.setResponseCode(HttpStatus.CREATED.value());
		} else {
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setResponseMessage("Please provide valid GameId");
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}
		return dto;
	}

	@Override
	public ResponseMessageDto addPlayer(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();

		if (gameMap.containsKey(gameId)) {

			long playerId = playerIdCounter.getAndIncrement() + 1;
			gameMap.get(gameId).getPlayerList().add(new Player(playerId, new ArrayList<Card>(), 0l));
			dto.setResponseMessage("Player has been Added.! Player Id is : " + playerId);
			dto.setError(false);
			dto.setResponseCode(HttpStatus.CREATED.value());

		} else {
			dto.setResponseMessage("Game Id not found for Adding Player");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}

		return dto;
	}

	@Override
	public ResponseMessageDto removePlayer(long gameId, long playerId) {
		ResponseMessageDto dto = new ResponseMessageDto();

		if (gameMap.containsKey(gameId)) {

			List<Player> playerList = gameMap.get(gameId).getPlayerList();
			if (null != playerList && !playerList.isEmpty()) {
				boolean result = playerList.removeIf(player -> player.getPlayerId() == playerId);
				if (result) {
					dto.setResponseMessage("Player is deleted from the Game");
					dto.setError(false);
					dto.setResponseCode(HttpStatus.OK.value());
				} else {
					dto.setResponseMessage("Player is not available in Game");
				}
			} else {
				dto.setResponseMessage("Player is not available in Game");
			}

		} else {
			dto.setResponseMessage("Game Id not found for remove Player");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}

		return dto;
	}

	@Override
	public ResponseMessageDto dealCards(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {

			Game game = gameMap.get(gameId);

			List<Player> playerList = game.getPlayerList();
			List<Card> cards = game.getCards();

			if (null != playerList && !playerList.isEmpty() && playerList.size() <= 52) {

				int numberOfPlayers = playerList.size();
				int cardsPerPlayer = cards.size() / numberOfPlayers;
				int remainingCards = cards.size() % numberOfPlayers;

				System.out.println("numberOfPlayers ->" + numberOfPlayers);
				System.out.println("cardsPerPlayer ->" + cardsPerPlayer);
				System.out.println("remainingCards ->" + remainingCards);

				ArrayList<Card> shuffledDeck = new ArrayList<>(cards);

				for (int player = 0; player < numberOfPlayers; ++player) {
					// if (player == remainingCards)
					// {
					// --cardsPerPlayer; // all remaining cards dealed
					// }

					List<Card> cardList = shuffledDeck.subList(0, cardsPerPlayer);
					playerList.get(player).getCards().addAll(cardList);
					long count = 0;
					for (Card card : cardList) {
						count = count + card.getRank().getPriority();
					}
					playerList.get(player).setTotalValue(count);
					// CardDeck hand = new CardDeck(shuffledDeck.subList(0, cardsPerPlayer));
					shuffledDeck.removeAll(cardList);
				}

				System.out.println("Left Card => " + shuffledDeck);

				game.setPlayerList(playerList);
				game.setLeftCards(shuffledDeck);
				gameMap.put(gameId, game);

				System.out.println("Game Map => " + gameMap);

				dto.setResponseMessage("Successfully deal all the Cards");
				dto.setError(false);
				dto.setResponseCode(HttpStatus.OK.value());

			} else {
				dto.setResponseMessage("Players not found or Players are more than 52 count");
			}

		} else {
			dto.setResponseMessage("Game Id not found for dealCards");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}
		return dto;
	}

	@Override
	public ResponseMessageDto getListofCardsForPlayer(long gameId, long playerId) {
		ResponseMessageDto dto = new ResponseMessageDto();

		if (gameMap.containsKey(gameId)) {

			List<Player> playerList = gameMap.get(gameId).getPlayerList();
			if (null != playerList && !playerList.isEmpty()) {
				Player player = playerList.stream().filter(p -> new Long(playerId).equals(new Long(p.getPlayerId())))
						.findAny().orElse(null);
				if (null != player) {
					// player.getCards().forEach(System.out::println);

					dto.setResponseObject(player);
					dto.setResponseMessage("Successfully fetched card for the player");
					dto.setResponseCode(HttpStatus.OK.value());
					dto.setError(false);

					System.out.println("getListofCardsForPlayer => " + dto);

				} else {
					dto.setResponseMessage("Player is not available in Game");
				}

			} else {
				dto.setResponseMessage("Player is not available in Game");
			}

		} else {
			dto.setResponseMessage("Game Id not found for remove Player");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}

		return dto;
	}

	@Override
	public ResponseMessageDto getListofPlayer(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {
			List<String> list = new ArrayList<>();
			List<Player> playerList = gameMap.get(gameId).getPlayerList();
			if (null != playerList && !playerList.isEmpty()) {

				playerList.sort(
						(Player p1, Player p2) -> new Long(p2.getTotalValue()).compareTo(new Long(p1.getTotalValue())));
				// playerList.sort((Player o1, Player
				// o2)->o1.getTotalValue()-o2.getTotalValue());
				playerList.forEach(p -> list.add(p.getPlayerId() + " " + p.getTotalValue()));

				dto.setResponseMessage("getListofPlayer fetched Successfully.!");
				dto.setResponseObject(list);
				dto.setResponseCode(HttpStatus.OK.value());
				dto.setError(false);

				System.out.println("getListofPlayer => " + dto);

			} else {
				dto.setResponseMessage("Players are not available in Game");
			}
		} else {
			dto.setResponseMessage("Game Id not found for getListofPlayer");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}
		return dto;
	}

	@Override
	public ResponseMessageDto getCountOfLeftCards(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {
			List<String> CardList = new ArrayList<>();
			List<Card> leftCards = gameMap.get(gameId).getLeftCards();

			if (null != leftCards && !leftCards.isEmpty()) {

				long SpadeCount = leftCards.stream().filter(card -> "Spade".equals(card.getSuit().toString()))
						.collect(Collectors.toList()).size();
				long HeartCount = leftCards.stream().filter(card -> "Heart".equals(card.getSuit().toString()))
						.collect(Collectors.toList()).size();
				long DiamondCount = leftCards.stream().filter(card -> "Diamond".equals(card.getSuit().toString()))
						.collect(Collectors.toList()).size();
				long ClubsCount = leftCards.stream().filter(card -> "Clubs".equals(card.getSuit().toString()))
						.collect(Collectors.toList()).size();

				CardList.add(SpadeCount + " Spade");
				CardList.add(HeartCount + " Heart");
				CardList.add(DiamondCount + " Diamond");
				CardList.add(ClubsCount + " Clubs");

				dto.setResponseObject(CardList);
				dto.setResponseMessage("getCountOfLeftCards fetched Successfully.!");
				dto.setResponseCode(HttpStatus.OK.value());
				dto.setError(false);

				System.out.println("getCountOfLeftCards => " + dto);

			} else {
				dto.setResponseMessage("No cards are left in the Game");
			}

		} else {
			dto.setResponseMessage("Game Id not found for getCountOfLeftCards");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}
		return dto;
	}

	@Override
	public ResponseMessageDto getCountOfEachCard(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {
			Map<String, List<Card>> mapCard = new LinkedHashMap<>();
			List<Card> leftCards = gameMap.get(gameId).getLeftCards();

			if (null != leftCards && !leftCards.isEmpty()) {

				List<Card> SpadeList = leftCards.stream().filter(card -> "Spade".equals(card.getSuit().toString()))
						.collect(Collectors.toList());
				List<Card> HeartList = leftCards.stream().filter(card -> "Heart".equals(card.getSuit().toString()))
						.collect(Collectors.toList());
				List<Card> DiamondList = leftCards.stream().filter(card -> "Diamond".equals(card.getSuit().toString()))
						.collect(Collectors.toList());
				List<Card> ClubsList = leftCards.stream().filter(card -> "Clubs".equals(card.getSuit().toString()))
						.collect(Collectors.toList());

				Comparator<Card> priorityCoparator = (o1, o2) -> o2.r.getPriority() - (o1.r.getPriority());

				SpadeList.sort(priorityCoparator);
				HeartList.sort(priorityCoparator);
				DiamondList.sort(priorityCoparator);
				ClubsList.sort(priorityCoparator);

				mapCard.put("Heart", HeartList);
				mapCard.put("Spade", SpadeList);
				mapCard.put("Clubs", ClubsList);
				mapCard.put("Diamond", DiamondList);

				dto.setResponseObject(mapCard);
				dto.setResponseMessage("getCountOfLeftCards fetched Successfully.!");
				dto.setResponseCode(HttpStatus.OK.value());
				dto.setError(false);

				System.out.println("getCountOfEachCard => " + dto);

			} else {
				dto.setResponseMessage("No cards are left in the Game");
			}

		} else {
			dto.setResponseMessage("Game Id not found for getCountOfLeftCards");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}
		return dto;
	}

	@Override
	public ResponseMessageDto shuffle(long gameId) {
		ResponseMessageDto dto = new ResponseMessageDto();
		if (gameMap.containsKey(gameId)) {

			Game game = gameMap.get(gameId);
			List<Card> cards = game.getCards();
			List<Card> randomList = new ArrayList<>();
			List<Integer> randomNumber = new ArrayList<>();
			ThreadLocalRandom.current().ints(0, 51).distinct().limit(51).forEach(n -> randomNumber.add(n));
			for (Integer integer : randomNumber) {
				randomList.add(cards.get(integer));
			}
			game.setCards(randomList);
			gameMap.put(gameId, game);
			// System.out.println("After "+gameMap);
			dto.setResponseCode(HttpStatus.OK.value());
			dto.setError(false);
			dto.setResponseMessage("Successfully shuffled.!");

		} else {
			dto.setResponseMessage("Game Id not found for shuffle");
			dto.setResponseCode(HttpStatus.NOT_FOUND.value());
			dto.setErrorDiscription("Game Id not found ");
			dto.setError(true);
		}

		return dto;
	}
	
//	public static void main(String args[]) {
//		DeckOfCardsImpl deck = new DeckOfCardsImpl();
//
//		deck.createGame();
//		deck.shuffle(1);
//
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//		deck.addPlayer(1);
//
//		deck.dealCards(1);
//
//		deck.getListofCardsForPlayer(1, 1);
//		deck.getListofPlayer(1);
//		deck.getCountOfLeftCards(1);
//		deck.getCountOfEachCard(1);
//
//	}

}
