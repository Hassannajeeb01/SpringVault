package com.example.springvault.model;

import java.util.List;

import com.example.springvault.model.GameState.Turn;
import com.example.springvault.model.GameState.Winner;

public class GameResponseDTO {
    
    private String gameID;
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private int playerHandValue;
    private int playerHandValueWithAceLogic;
    private int dealerHandValue;
    private int dealerHandValueWithAceLogic;
    private Winner winner;
    private Turn turn;

    public GameResponseDTO(GameState gameState) {
        // need to send gameid, all cards for player and dealer, turn, winner -- just deck is missing
        this.gameID = gameState.getGameID();
        this.playerCards = gameState.getPlayer().getCards();
        this.dealerCards = gameState.getDealer().getCards();
        this.playerHandValue = gameState.getPlayer().getHandValue();
        this.playerHandValueWithAceLogic = gameState.getPlayer().getHandValueWithAceLogic();
        this.dealerHandValue = gameState.getDealer().getHandValue();
        this.dealerHandValueWithAceLogic = gameState.getDealer().getHandValueWithAceLogic();
        this.winner = gameState.getWinner();
        this.turn = gameState.getTurn();
    }

    // Getters and setters

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public int getPlayerHandValue() {
        return playerHandValue;
    }

    public void setPlayerHandValue(int playerHandValue) {
        this.playerHandValue = playerHandValue;
    }

    public int getPlayerHandValueWithAceLogic() {
        return playerHandValueWithAceLogic;
    }

    public void setPlayerHandValueWithAceLogic(int playerHandValueWithAceLogic) {
        this.playerHandValueWithAceLogic = playerHandValueWithAceLogic;
    }

    public int getDealerHandValue() {
        return dealerHandValue;
    }

    public void setDealerHandValue(int dealerHandValue) {
        this.dealerHandValue = dealerHandValue;
    }

    public int getDealerHandValueWithAceLogic() {
        return dealerHandValueWithAceLogic;
    }

    public void setDealerHandValueWithAceLogic(int dealerHandValueWithAceLogic) {
        this.dealerHandValueWithAceLogic = dealerHandValueWithAceLogic;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }
}
