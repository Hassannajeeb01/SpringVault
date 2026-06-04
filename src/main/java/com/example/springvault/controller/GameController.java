package com.example.springvault.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.springvault.model.Deck;
import com.example.springvault.model.Participant;
import com.example.springvault.service.BlackJackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class GameController {
    @Autowired
    private BlackJackService blackJackService;

    @PostMapping("/deal")
    public ArrayList<Participant> deal() {
        
        return blackJackService.deal();
    }
    
}
