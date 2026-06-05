package com.example.springvault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springvault.model.GameResponseDTO;
import com.example.springvault.service.BlackJackService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin(origins = "*")
@RestController
public class GameController {
    @Autowired
    private BlackJackService blackJackService;

    @PostMapping("/startGame")
    public GameResponseDTO startGame(@RequestBody String playerName) {
        return blackJackService.startGame(playerName);
    }

    @PostMapping("/hit")
    public GameResponseDTO hit(@RequestBody String gameId) {
        return blackJackService.hit(gameId);
    }

    @PostMapping("/stand")
    public GameResponseDTO stand(@RequestBody String gameID) {
        return blackJackService.stand(gameID);
    }
    
    
    
}
