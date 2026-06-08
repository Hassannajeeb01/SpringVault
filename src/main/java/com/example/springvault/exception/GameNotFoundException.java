package com.example.springvault.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameID) {
        super("Game not found: " + gameID);
    }
}
