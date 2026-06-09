package com.example.springvault.controller;

import com.example.springvault.service.LeaderboardService;
import org.springframework.web.bind.annotation.RestController;

import com.example.springvault.model.GameResponseDTO;
import com.example.springvault.model.LeaderboardDTO;
import com.example.springvault.service.BlackJackService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;



@CrossOrigin(origins = "*")
@RestController
public class GameController {
    private final BlackJackService blackJackService;
    private final LeaderboardService leaderboardService;

    public GameController(BlackJackService blackJackService, LeaderboardService leaderboardService) {
        this.blackJackService = blackJackService;
        this.leaderboardService = leaderboardService;
    }

    @PostMapping("/startGame")
    public GameResponseDTO startGame(@RequestBody String playerName,  @RequestHeader("X-Session-Id") String sessionId) {
        return blackJackService.startGame(playerName, sessionId);
    }

    @PostMapping("/hit")
    public GameResponseDTO hit(@RequestBody String gameID) {
        return blackJackService.hit(gameID);
    }

    @PostMapping("/stand")
    public GameResponseDTO stand(@RequestBody String gameID) {
        return blackJackService.stand(gameID);
    }
    
    @GetMapping("/leaderboard")
    public List<LeaderboardDTO> getLeaderboard() {
        return leaderboardService.getLeaderboard();
    }
    
    
    
}
