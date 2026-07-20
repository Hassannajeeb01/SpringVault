package com.example.springvault.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springvault.dto.LeaderboardDTO;
import com.example.springvault.model.LeaderboardEntity;
import com.example.springvault.repository.LeaderboardRepository;

import jakarta.transaction.Transactional;

@Service
public class LeaderboardService {
    
    private LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    @Transactional
    public List<LeaderboardDTO> getLeaderboard() {

        // get the leaderbord entries
        List<LeaderboardEntity> entries = leaderboardRepository
            .findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(1);

        // stream them into DTO list

        List<LeaderboardDTO> result = new ArrayList<>();
        
        for (LeaderboardEntity entry : entries) {
            result.add(new LeaderboardDTO(
                entry.getEmailId(),
                entry.getPlayerName(),
                entry.getTotalGames(),
                entry.getTotalPoints(),
                entry.getPointsPergame()
            ));
        }

        return result;
        
    }

}
