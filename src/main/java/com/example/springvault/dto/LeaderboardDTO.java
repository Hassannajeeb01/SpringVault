package com.example.springvault.dto;

public class LeaderboardDTO {
    private String playerName;
    private int totalGames;
    private int totalPoints;
    private double pointsPerGame;

    public LeaderboardDTO() {} // for jackson/redis

    public LeaderboardDTO(String playerName, int totalGames, int totalPoints, double pointsPerGame) {
        this.playerName = playerName;
        this.totalGames = totalGames;
        this.totalPoints = totalPoints;
        this.pointsPerGame = pointsPerGame;
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

    public double getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(double pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

}
