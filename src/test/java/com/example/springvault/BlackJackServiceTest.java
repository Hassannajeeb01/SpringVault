package com.example.springvault;

import com.example.springvault.model.Card;
import com.example.springvault.model.GameState;
import com.example.springvault.model.GameState.Turn;
import com.example.springvault.model.GameState.Winner;
import com.example.springvault.repository.LeaderboardRepository;
import com.example.springvault.model.Participant;
import com.example.springvault.service.BlackJackService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackJackServiceTest {

	RedisTemplate<String, GameState> redisTemplate = Mockito.mock();
	private LeaderboardRepository leaderboardRepository = Mockito.mock();

    BlackJackService service = new BlackJackService(redisTemplate, leaderboardRepository);

    @Test
    void playerBusts_dealerWins() {
        // Arrange
		GameState gameState = new GameState("testID", "Session 1", null, new Participant("John Doe"), 
			new Participant(true), Turn.PLAYER);
        
        gameState.getPlayer().addCard(new Card("Hearts", "K"));
        gameState.getPlayer().addCard(new Card("Spades", "K"));
        gameState.getPlayer().addCard(new Card("Diamonds", "5"));
        // player hand = 25, busted
        
        // Act
        Winner result = service.analyzeGame(gameState);
        
        // Assert
        assertEquals(Winner.DEALER, result);
    }

	@Test
	void tie() {
		// Arrange
		GameState gameState = new GameState("Test", "Session 1", null, new Participant("John Doe"), 
			new Participant(true), Turn.DEALER);

		gameState.getPlayer().addCard(new Card("Hearts", "K"));
		gameState.getDealer().addCard(new Card("Hearts", "Q"));

		// Act
		Winner result = service.analyzeGame(gameState);

		// Assert
		assertEquals(Winner.PUSH, result);
	}

}