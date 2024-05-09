package com.craftDemo.gamingLeaderboard.service.impl;

import com.craftDemo.gamingLeaderboard.constants.GAMING_CONSTANTS;
import com.craftDemo.gamingLeaderboard.exceptions.CacheInitializationException;
import com.craftDemo.gamingLeaderboard.exceptions.CacheUpdateFailureException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardNotInitializedException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.repository.playerScoreRepository;
import com.craftDemo.gamingLeaderboard.service.cacheService;
import com.craftDemo.gamingLeaderboard.service.leaderBoardService;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionToLeaderBoards;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class leaderBoardServiceImpl implements leaderBoardService {

    @Autowired
    cacheService<playerScore> cache;

    @Autowired
    playerScoreRepository scoreRepository;

    @Autowired
    scoreIngestionToLeaderBoards scoreIngestor;

    boolean leaderBoardInitialized;

    Logger logger = LoggerFactory.getLogger(leaderBoardServiceImpl.class);

    @PostConstruct
    public void createBoard() throws LeaderboardNotInitializedException {
        initializeBoard(GAMING_CONSTANTS.DEFAULT_LEADERBOARD_SIZE);
    }

    private void initializeBoard(int topN) throws LeaderboardNotInitializedException {
        try {
            List<playerScore> allScores = scoreRepository.findAll();
            cache.initialize(topN, allScores);
            scoreIngestor.registerLeaderBoard(this);
            leaderBoardInitialized = true;
        } catch (CacheInitializationException e) {
            logger.error("Leader Board Initialization Failed - {}", e.getMessage());
            throw new LeaderboardNotInitializedException(e.getMessage());
        }
    }

    public void createBoard(int topN) throws LeaderboardNotInitializedException {
        initializeBoard(topN);
    }

    public List<playerScore> getTopNPlayers() throws LeaderboardNotInitializedException {
        if (!leaderBoardInitialized) {
            logger.error("Leader Board Not Initialized - Cannot retrieve top players");
            throw new LeaderboardNotInitializedException("LeaderBoard not yet initialized");
        }
        return cache.getTopNplayers();
    }

    public void publish(playerScore newScore) throws LeaderboardUpdateFailureException {
        try {
            cache.addtoCache(newScore);
        } catch (CacheUpdateFailureException e) {
            logger.error("Leader Board Update failed - {}", e.getMessage());
            throw new LeaderboardUpdateFailureException(e.getMessage());
        }
    }

}

