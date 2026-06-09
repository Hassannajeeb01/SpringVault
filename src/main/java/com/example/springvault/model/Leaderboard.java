package com.example.springvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Leaderboard")
public class Leaderboard {
    
    @Id
    private String sessionID; // unique player

    private String playerName;
    private int totalGames;
    private int totalPoints;

    private double getPointsPergame() {
        return totalGames == 0 ? 0 : (double) totalPoints / totalGames;
    }
}
