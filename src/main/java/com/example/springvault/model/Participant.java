package com.example.springvault.model;

import java.util.ArrayList;

public class Participant {

    private final boolean isDealer;
    private ArrayList<Card> cards;

    public Participant(boolean isDealer) {
        this.isDealer = isDealer;
        this.cards = new ArrayList<>();
    }

    // Getters
    public boolean isDealer() {
        return isDealer;
    }

    public ArrayList<Card> getCards () {
        return this.cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public int getHandValue() {
        int value = 0;
        for (Card card : this.cards) {
            value = value + card.getValue();
        }
        return value;
    }

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

