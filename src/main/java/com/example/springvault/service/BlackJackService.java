package com.example.springvault.service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.springvault.exception.GameNotFoundException;
import com.example.springvault.model.*;
import com.example.springvault.model.GameState.Turn;
import com.example.springvault.model.GameState.Winner;

@Service
public class BlackJackService {
        // private final ConcurrentHashMap<String, GameState> games = new ConcurrentHashMap<>(); // String is gameID
        private final RedisTemplate<String, GameState> redisTemplate;
        
        public BlackJackService (RedisTemplate<String, GameState> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        public GameResponseDTO startGame(String playerName, String sessionID) {
            // Initialize a player participant with player name
            Participant player = new Participant(playerName);

            // Init dealer participant
            Participant dealer = new Participant(false);

            // Init a deck
            Deck deck = Deck.newDeck();

            // Deal cards to both participants, keeping one of the dealers card faceup
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());

            // add cards for dealer
            dealer.addCard(deck.drawCard().setFaceDown(true)); // the first card is facedown
            dealer.addCard(deck.drawCard());

            // init gameState, generate gameid, add it to games
            String gameID = UUID.randomUUID().toString();
            GameState gameState = new GameState(gameID, sessionID, deck, player, dealer, Turn.PLAYER);

            // add gamestate to the games
            // games.put(gameID, gameState);
            redisTemplate.opsForValue().set("Game: " + gameID, gameState);

            return new GameResponseDTO(gameState);
        }

        public GameResponseDTO hit (String gameID) {
            GameState gameState = getGameState(gameID);

            if (gameState.getTurn() == Turn.PLAYER){
                // Deck.drawCard()
                gameState.getPlayer().addCard(gameState.getDeck().drawCard());
            }
            else {
                // logic for dealer hitting
                if (gameState.getDealer().getHandValue() < 17 ) {
                    gameState.getDealer().addCard(gameState.getDeck().drawCard());
                } 
            }
            
            // update gamestate with winner if there is one
            gameState.setWinner(analyzeGame(gameState));

            // if player bust on their turn, flip dealer card
            if (gameState.getWinner() == Winner.DEALER && gameState.getTurn() == Turn.PLAYER) {
                gameState.getDealer().getCards().get(0).setFaceDown(false);
            }

            redisTemplate.opsForValue().set("Game: " + gameID, gameState);

            return new GameResponseDTO(gameState);
        }

        public GameResponseDTO stand (String gameID) {
            GameState gameState = getGameState(gameID);

            // AnalyzeGame and update gamestate
            gameState.setWinner(analyzeGame(gameState));

            // update turn
            gameState.setTurn(Turn.DEALER);

            // flip the dealer card
            gameState.getDealer().getCards().get(0).setFaceDown(false);

            redisTemplate.opsForValue().set("Game: " + gameID, gameState);

            return new GameResponseDTO(gameState);
        }

        public Winner analyzeGame(GameState gameState) {
            // check whose turn it is
            // if player, 
                // check if == 21, player wins
                // if > 21 bust i.e dealer wins
            int playerHandValue = calculateHandValue(gameState.getPlayer());
            int dealerHandValue = calculateHandValue(gameState.getDealer());

            if (gameState.getTurn() == Turn.PLAYER) {
                if (playerHandValue == 21) {
                    return Winner.PLAYER;
                }
                else if (playerHandValue > 21) {
                    return Winner.DEALER;
                }
                else {
                    return Winner.NONE;
                }
            }
            else if (gameState.getTurn() == Turn.DEALER) {

                if (dealerHandValue > 21) {
                    return Winner.PLAYER;
                }
                else if (playerHandValue == dealerHandValue) {
                    return Winner.PUSH;
                }
                else if (playerHandValue > dealerHandValue) {
                    return Winner.PLAYER;
                }
                else if (playerHandValue < dealerHandValue) {
                    return Winner.DEALER;
                }
            }
            return Winner.NONE;
        }

        private GameState getGameState(String gameID) {
            // return Optional.ofNullable(games.get(gameID))
                // .orElseThrow(() -> new GameNotFoundException(gameID));

            return Optional.ofNullable(redisTemplate.opsForValue().get("Game: " + gameID))
                .orElseThrow(() -> new GameNotFoundException(gameID));
        }

        public int calculateHandValue(Participant participant) {
            int handValue = participant.getHandValue();
            int aces = participant.getAces();

            while (aces > 0 && handValue > 21) {
                handValue = handValue - 10; // initially aces are counted as 11, subtract 10 to make it 1
                aces --;
            }

            return handValue;
        }
}

