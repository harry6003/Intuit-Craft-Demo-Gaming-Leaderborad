package com.craftDemo.gamingLeaderboard.service.impl;

import com.craftDemo.gamingLeaderboard.exceptions.CacheInitializationException;
import com.craftDemo.gamingLeaderboard.exceptions.CacheUpdateFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.service.cacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class cacheServiceImpl implements cacheService<playerScore> {

    int topN;
    PriorityQueue<playerScore> minHeap;
    Map<String, playerScore> playerToScore;

    Logger logger = LoggerFactory.getLogger(cacheServiceImpl.class);

    public void initialize(int topN, List<playerScore> dataSet) throws CacheInitializationException {
        this.topN = topN;
        try {
            minHeap = new PriorityQueue<>();
            playerToScore = new HashMap<>();
            for (playerScore score : dataSet) {
                if (minHeap.size() < topN) {
                    minHeap.add(score);
                    playerToScore.put(score.getPlayerId(), score);
                } else {
                    if (score.getScore() > (minHeap.peek() != null ? minHeap.peek().getScore() : 0)) {
                        playerScore removedScore = minHeap.poll();
                        minHeap.add(score);
                        playerToScore.remove(removedScore != null ? removedScore.getPlayerId() : null);
                        playerToScore.put(score.getPlayerId(), score);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Failed to initialize cache - {}", e.getMessage());
            throw new CacheInitializationException("Failed to initialize cache");
        }
    }

    public void addtoCache(playerScore score) throws CacheUpdateFailureException {
        try {
            if (playerToScore.containsKey(score.getPlayerId())) {
                playerScore scoreToBeUpdated = playerToScore.get(score.getPlayerId());

                if (scoreToBeUpdated.getScore() < score.getScore()) {
                    logger.debug("Updating {} to {}", scoreToBeUpdated.getPlayerId(), score.getScore());
                    minHeap.remove(scoreToBeUpdated);
                    playerToScore.put(score.getPlayerId(), score);
                    minHeap.add(score);
                }
                return;
            }
            if (minHeap.size() < topN) {
                minHeap.add(score);
                playerToScore.put(score.getPlayerId(), score);
            } else {
                if (score.getScore() > (minHeap.peek() != null ? minHeap.peek().getScore() : 0)) {
                    playerScore removedScore = minHeap.poll();
                    minHeap.add(score);
                    playerToScore.remove(removedScore != null ? removedScore.getPlayerId() : null);
                    playerToScore.put(score.getPlayerId(), score);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to update cache - {}", e.getMessage());
            throw new CacheUpdateFailureException(e.getMessage());
        }

    }

    public List<playerScore> getTopNplayers() {
        List<playerScore> res = new ArrayList<>(minHeap);
        res.sort(Collections.reverseOrder());
        return res;
    }

}

