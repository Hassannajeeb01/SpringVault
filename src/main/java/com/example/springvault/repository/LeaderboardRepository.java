package com.example.springvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springvault.model.LeaderboardEntry;

public interface LeaderboardRepository extends JpaRepository <LeaderboardEntry, String> {
    List<LeaderboardEntry> findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(int minGames);
}
