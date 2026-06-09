package com.example.springvault.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Participant {

    private String name;
    private boolean isDealer;
    private ArrayList<Card> cards;

    public Participant() {} // for jackson/redis

    public Participant(String name) {
        this.isDealer = false;
        this.cards = new ArrayList<>();
        this.name = name;
        
    }

    public Participant(boolean isDealer) {
        this.isDealer = isDealer;
        this.cards = new ArrayList<>();
        this.name = "Dealer";
    }


    // Getters

    public String getName() {
        return name;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public ArrayList<Card> getCards () {
        return this.cards;
    }

    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    @JsonIgnore
    public int getHandValue() {
        int value = 0;
        for (Card card : this.cards) {
            value = value + card.getValue();
        }
        return value;
    }

    @JsonIgnore
    public int getAces() {
        int aces = 0;
        for (Card card: this.cards) {
            if (card.getRank().equals("A")) {
                aces++;
            }
        }
        return aces;
    }


}

