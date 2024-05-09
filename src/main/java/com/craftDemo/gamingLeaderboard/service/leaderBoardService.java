package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.CacheInitializationException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardNotInitializedException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;

import java.util.List;

public interface leaderBoardService {
    public void createBoard(int topN) throws CacheInitializationException, LeaderboardNotInitializedException;
    public List<playerScore> getTopNPlayers() throws LeaderboardNotInitializedException;
    public void publish(playerScore newScore) throws LeaderboardUpdateFailureException;
}
