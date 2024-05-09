package com.craftDemo.gamingLeaderboard.controller;

import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class scoreUpdateController {

    @Autowired
    scoreIngestionService scoreIngestor;

    Logger logger = LoggerFactory.getLogger(scoreUpdateController.class);

    @PostMapping("/updateScore")
    public void updateScore(@RequestBody playerScore newScore) {
        try {
            scoreIngestor.publish(newScore);
        } catch (Exception e) {
            logger.error("Leaderboard Update failed - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

