package com.example.springvault.controller;

import com.example.springvault.service.LeaderboardService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;

import com.example.springvault.dto.GameResponseDTO;
import com.example.springvault.dto.LeaderboardDTO;
import com.example.springvault.service.BlackJackService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public GameResponseDTO startGame(@RequestBody String playerName, HttpServletRequest request ) {
        String emailId = (String) request.getAttribute("email"); // get Email identifier
        return blackJackService.startGame(playerName, emailId);
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
