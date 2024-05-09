package com.craftDemo.gamingLeaderboard.controller;

import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardNotInitializedException;
import com.craftDemo.gamingLeaderboard.model.leaderBoard;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.service.leaderBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class leaderboardController {

    @Autowired
    leaderBoardService leaderBoardService;

    Logger logger = LoggerFactory.getLogger(leaderboardController.class);

    @GetMapping("/getTopScorers")
    public List<playerScore> getTopScorers() {
        try {
            return leaderBoardService.getTopNPlayers();
        } catch (LeaderboardNotInitializedException e) {
            logger.error("Leaderboard not initialized - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please register/create LeaderBoard first");
        } catch (Exception e) {
            logger.error("Couldn't get top scores - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/createBoard")
    public void createBoard(@RequestBody leaderBoard in) {
        try {
            leaderBoardService.createBoard(in.getLeaderBoardSize());
        } catch (Exception e) {
            logger.error("Leaderboard creation failed - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

