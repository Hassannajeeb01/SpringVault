package com.example.springvault.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springvault.model.LeaderboardDTO;
import com.example.springvault.model.LeaderboardEntry;
import com.example.springvault.repository.LeaderboardRepository;

import jakarta.transaction.Transactional;

@Service
public class LeaderboardService {
    
    @Autowired
    LeaderboardRepository leaderboardRepository;

    @Transactional
    public List<LeaderboardDTO> getLeaderboard() {

        // get the leaderbord entries
        List<LeaderboardEntry> entries = leaderboardRepository
            .findByTotalGamesGreaterThanEqualOrderByPointsPerGameDesc(1);

        // stream them into DTO list

        List<LeaderboardDTO> result = new ArrayList<>();
        
        for (LeaderboardEntry entry : entries) {
            result.add(new LeaderboardDTO(
                entry.getPlayerName(),
                entry.getTotalGames(),
                entry.getTotalPoints(),
                entry.getPointsPergame()
            ));
        }

        return result;
        
    }

}
