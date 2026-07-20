package com.example.springvault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Leaderboard")
public class LeaderboardEntity {
    
    @Id
    private String emailId; // unique identifier
    private String playerName;
    private int totalGames;
    private int totalPoints;
    private double pointsPerGame;

    public LeaderboardEntity() {} // for JPA / jackson

    public LeaderboardEntity(String emailId, String playerName) {
        this.emailId = emailId;
        this.playerName = playerName;
        totalGames = 0;
        totalPoints = 0;
        pointsPerGame = 0;
    }

    public double getPointsPergame() {
        return pointsPerGame;
    }

    public void setPointsPergame(double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

}
