package com.example.springvault.service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.springvault.model.*;
import com.example.springvault.model.GameState.Turn;
import com.example.springvault.model.GameState.Winner;

@Service
public class BlackJackService {
        private final ConcurrentHashMap<String, GameState> games = new ConcurrentHashMap<>(); // String is gameID

        public GameResponseDTO startGame(String playerName) {
            // Initialize a player participant with player name
            Participant player = new Participant(playerName);

            // Init dealer participant
            Participant dealer = new Participant(false);

            // Init a deck
            Deck deck = new Deck();

            // Deal cards to both participants, keeping one of the dealers card faceup
            player.addCard(deck.drawCard());
            player.addCard(deck.drawCard());

            // add cards for dealer
            dealer.addCard(deck.drawCard().setFaceDown(true)); // the first card is facedown
            dealer.addCard(deck.drawCard());

            // init gameState, generate gameid, add it to games
            String gameID = UUID.randomUUID().toString();
            GameState gameState = new GameState(gameID, deck, player, dealer, Turn.PLAYER);

            // add gamestate to the games
            games.put(gameID, gameState);

            return new GameResponseDTO(gameState);
        }

        public GameResponseDTO hit (String gameID) {
            GameState gameState = games.get(gameID);

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

            return new GameResponseDTO(gameState);
        }

        public GameResponseDTO stand (String gameID) {
            GameState gameState = games.get(gameID); 

            // AnalyzeGame and update gamestate
            gameState.setWinner(analyzeGame(gameState));

            // update turn
            gameState.setTurn(Turn.DEALER);

            // flip the dealer card
            gameState.getDealer().getCards().get(0).setFaceDown(false);

            return new GameResponseDTO(gameState);
        }

        private Winner analyzeGame(GameState gameState) {
            // check whose turn it is
            // if player, 
                // check if == 21, player wins
                // if > 21 bust i.e dealer wins
            if (gameState.getTurn() == Turn.PLAYER) {
                if (gameState.getPlayer().getHandValue() == 21) {
                    return Winner.PLAYER;
                }
                else if (gameState.getPlayer().getHandValue() > 21) {
                    return Winner.DEALER;
                }
                else {
                    return Winner.NONE;
                }
            }
            if (gameState.getTurn() == Turn.DEALER) {
                int dealerHandValue = gameState.getDealer().getHandValue();
                int playerHandValue = gameState.getPlayer().getHandValue();

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
}
