package com.example.springvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springvault.model.LeaderboardEntry;

@Repository
public interface LeaderboardRepository extends JpaRepository <LeaderboardEntry, String> {
    List<LeaderboardEntry> findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(int minGames);
}
