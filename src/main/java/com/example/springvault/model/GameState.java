package com.example.springvault.model;

public class GameState {
    
    private Participant player;
    private Participant dealer;
    private Deck deck;
    private String gameID;
    private Turn turn;
    private Winner winner;

    public enum Turn {
        PLAYER, DEALER
    }

    public enum Winner {
        PLAYER, DEALER, PUSH, NONE
    }

    public GameState(String gameID, Deck deck, Participant player, Participant dealer, Turn turn) {
        this.gameID = gameID;
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
        this.turn = turn;
        this.winner = Winner.NONE;
    }
    // Getters and Setters
    
    public Participant getPlayer() {
        return player;
    }

    public void setPlayer(Participant player) {
        this.player = player;
    }

    public Participant getDealer() {
        return dealer;
    }

    public void setDealer(Participant dealer) {
        this.dealer = dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public Winner getWinner() {
        return winner;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }
}   
