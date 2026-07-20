package com.example.springvault.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springvault.model.LeaderboardEntity;

public interface LeaderboardRepository extends JpaRepository <LeaderboardEntity, UUID> {
    List<LeaderboardEntity> findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(int minGames);
    Optional<LeaderboardEntity> findByEmailId(String email);
}
