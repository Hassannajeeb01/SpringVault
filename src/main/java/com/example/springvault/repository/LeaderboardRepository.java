package com.example.springvault.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springvault.model.LeaderboardEntity;

public interface LeaderboardRepository extends JpaRepository <LeaderboardEntity, String> {
    List<LeaderboardEntity> findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(int minGames);
}
