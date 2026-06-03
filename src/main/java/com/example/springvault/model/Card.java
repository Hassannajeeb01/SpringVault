package com.example.springvault.model;

import java.util.Map;

public class Card {
    
    private final String suit;
    private final String rank;
    private boolean isFaceDown = true;

    // Map for stroring the value of the card
    private static final Map<String, Integer> cardValues = Map.ofEntries(
            Map.entry("2", 2),
            Map.entry("3", 3),
            Map.entry("4", 4),
            Map.entry("5", 5),
            Map.entry("6", 6),
            Map.entry("7", 7),
            Map.entry("8", 8),
            Map.entry("9", 9),
            Map.entry("10", 10),
            Map.entry("J", 10),
            Map.entry("Q", 10),
            Map.entry("K", 10),
            Map.entry("A", 11) // Ace can be worth 1 or 11, but we'll start with 11
    );

    // Constructors
    // Default
    public Card(String suit, String rank, boolean isFaceDown) {
        this.suit = suit;
        this.rank = rank;
        this.isFaceDown = isFaceDown;
    }

    public Card(String suit, String rank) {
    this(suit, rank, true);
}

    // Getters
    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public boolean isFaceDown   () {
        return isFaceDown;
    }

    public int getValue() {
        return cardValues.get(this.rank);
    }

    // Setters
    public void setFaceDown(boolean isFaceDown) {
        this.isFaceDown = isFaceDown;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
