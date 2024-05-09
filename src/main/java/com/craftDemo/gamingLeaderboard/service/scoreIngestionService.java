package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.DatabaseStorageException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;

public interface scoreIngestionService {
    public void publish(playerScore newScore) throws LeaderboardUpdateFailureException, DatabaseStorageException;
}
