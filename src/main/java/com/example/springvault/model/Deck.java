package com.example.springvault.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    
    private ArrayList<Card> cards = new ArrayList<>();
    private static final String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
    private static final String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

    public Deck() {} // kept empty so redis (jackson) can use this

    public static Deck newDeck() {
        Deck deck = new Deck();
        deck.initializeCards();
        return deck;
    }

    private void initializeCards() {
        // create a new deck
        for (String suit: suits) {
            for (String rank: ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        // shuffle the deck
        Collections.shuffle(cards);
    }

    // Getters

    public ArrayList<Card> getCards() {
        return cards;
    }

    // Setters
    
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }
        // remove card and return it to the caller
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
