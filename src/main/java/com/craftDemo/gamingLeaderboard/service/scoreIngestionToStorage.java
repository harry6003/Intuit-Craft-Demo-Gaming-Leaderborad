package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.DatabaseStorageException;
import com.craftDemo.gamingLeaderboard.model.playerScore;

public interface scoreIngestionToStorage {
    public void publishToStore(playerScore newScore) throws DatabaseStorageException;
}
