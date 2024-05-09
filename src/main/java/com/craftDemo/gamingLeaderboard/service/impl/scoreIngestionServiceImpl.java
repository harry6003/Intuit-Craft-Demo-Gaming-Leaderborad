package com.craftDemo.gamingLeaderboard.service.impl;


import com.craftDemo.gamingLeaderboard.exceptions.DatabaseStorageException;
import com.craftDemo.gamingLeaderboard.exceptions.LeaderboardUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.repository.playerScoreRepository;
import com.craftDemo.gamingLeaderboard.service.leaderBoardService;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionService;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionToLeaderBoards;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionToStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class scoreIngestionServiceImpl implements scoreIngestionToLeaderBoards, scoreIngestionToStorage, scoreIngestionService {

    List<leaderBoardService> leaderBoards = new ArrayList<>();

    @Autowired
    playerScoreRepository scoreRepository;

    public void publishToStore(playerScore newScore) throws DatabaseStorageException {
        try {
            Optional<playerScore> scoreAlreadyPresent = scoreRepository.findById(newScore.getPlayerId());
            if (scoreAlreadyPresent.isPresent() && scoreAlreadyPresent.get().getScore() >= newScore.getScore()) {
                return;
            }
            scoreRepository.save(newScore);
        } catch (Exception e) {
            throw new DatabaseStorageException("Could not publish data to storage");
        }

    }

    public void registerLeaderBoard(leaderBoardService leaderBoard) {
        leaderBoards.add(leaderBoard);
    }

    public void publishToLeaderBoards(playerScore newScore) throws LeaderboardUpdateFailureException {
        for (leaderBoardService leaderBoard : leaderBoards) {
            leaderBoard.publish(newScore);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void publish(playerScore newScore) throws LeaderboardUpdateFailureException, DatabaseStorageException {
        publishToStore(newScore);
        publishToLeaderBoards(newScore);
    }

}
