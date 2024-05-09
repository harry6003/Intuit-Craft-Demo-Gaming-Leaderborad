package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;

public interface scoreIngestionToLeaderBoards {
    public void registerLeaderBoard(leaderBoardService leaderBoard);
    public void publishToLeaderBoards(playerScore newScore) throws LeaderboardUpdateFailureException;
}
