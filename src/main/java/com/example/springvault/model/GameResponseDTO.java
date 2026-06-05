package com.example.springvault.model;

import java.util.List;

import com.example.springvault.model.GameState.Turn;
import com.example.springvault.model.GameState.Winner;

public class GameResponseDTO {
    
    private String gameID;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private Winner winner;
    private Turn turn;

    public GameResponseDTO(GameState gameState) {
        // need to send gameid, all cards for player and dealer, turn, winner -- just deck is missing
        this.gameID = gameState.getGameID();
        this.playerCards = gameState.getPlayer().getCards();
        this.dealerCards = gameState.getDealer().getCards();
        this.winner = gameState.getWinner();
        this.turn = gameState.getTurn();
    }

    // Getters

    public String getGameID() {
        return gameID;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public Winner getWinner() {
        return winner;
    }

    public Turn getTurn() {
        return turn;
    }
}
