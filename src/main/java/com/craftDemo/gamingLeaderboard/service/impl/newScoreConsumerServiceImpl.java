package com.craftDemo.gamingLeaderboard.service.impl;

import com.craftDemo.gamingLeaderboard.constants.GAMING_CONSTANTS;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.service.newDataConsumerService;
import com.craftDemo.gamingLeaderboard.service.scoreIngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class newScoreConsumerServiceImpl implements newDataConsumerService<playerScore> {

    @Autowired
    scoreIngestionService scoreIngestor;

    Logger logger = LoggerFactory.getLogger(newScoreConsumerServiceImpl.class);

    @KafkaListener(topics = GAMING_CONSTANTS.KAFKA_TOPIC, groupId = GAMING_CONSTANTS.KAFKA_GROUP_ID)
    public void consumeDataFromQueue(playerScore newData) {
        try {
            scoreIngestor.publish(newData);
        } catch (Exception e) {
            logger.error("Could not publish new score - {}", e.getMessage());
            return;
        }
        logger.debug("Published {}", newData);
    }

}
