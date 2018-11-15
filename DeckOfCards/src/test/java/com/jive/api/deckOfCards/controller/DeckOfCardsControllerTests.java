package com.jive.api.deckOfCards.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.jive.api.deckOfCards.DeckOfCardsApplication;
import com.jive.api.deckOfCards.dto.Game;

/**
 * @author Ashish.Patel
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DeckOfCardsApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * This Class will be test for DeckOfCardsController layer.
 */
public class DeckOfCardsControllerTests {

	private static Gson gson = new Gson();

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	private static final String UTF8 = "utf-8";
	private static final String responseCode = "$.responseCode";
	private static final String error = "$.error";
	
	
	//In this test1createGame method first checked all are the positive Test cases for the game id 1 with 3 players
	@Test
	public void test1createGame() throws Exception{
		
		Game game = new Game();
		game.setGameId(1l);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/createGame/").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		String input = gson.toJson(game);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/createDeck/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/addPlayer/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/addPlayer/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/addPlayer/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/addPlayer/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.CREATED.value()))
				.andExpect(jsonPath(error).value(false));
		
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deckofCards/removePlayer/1/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/shuffle/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/dealCards/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getListofCardsForPlayer/1/2").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getListofPlayer/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getCountOfLeftCards/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getCountOfEachCard/1").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.OK.value()))
				.andExpect(jsonPath(error).value(false));
		
	}
	
	//checking for all negative test cases for the game 
	
	@Test
	public void test2DeleteGameWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/deckofCards/deleteGame/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test3addPlayerWithInvalidGameId() throws Exception{
		Game game = new Game();
		game.setGameId(10l);
		String input = gson.toJson(game);
		mockMvc.perform(MockMvcRequestBuilders.post("/deckofCards/addPlayer/").contentType(MediaType.APPLICATION_JSON)
				.content(input).characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test4ShuffleWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/shuffle/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test5DealCardsWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/dealCards/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test6GetListofCardsForPlayerWithInvalidGameIdandPlayerId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getListofCardsForPlayer/10/20").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test7GetListofPlayerWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getListofPlayer/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test8GetCountOfLeftCardsWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getCountOfLeftCards/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	@Test
	public void test9getCountOfEachCardWithInvalidGameId() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/deckofCards/getCountOfEachCard/10").contentType(MediaType.APPLICATION_JSON)
				.content("").characterEncoding(UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath(responseCode).value(HttpStatus.NOT_FOUND.value()))
				.andExpect(jsonPath(error).value(true));
	}
	
	
	
	
}
