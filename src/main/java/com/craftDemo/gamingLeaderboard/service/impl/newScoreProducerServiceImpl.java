package com.craftDemo.gamingLeaderboard.service.impl;

import com.craftDemo.gamingLeaderboard.constants.GAMING_CONSTANTS;
import com.craftDemo.gamingLeaderboard.exceptions.MessageQueueFailureException;
import com.craftDemo.gamingLeaderboard.model.playerScore;
import com.craftDemo.gamingLeaderboard.service.newDataProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class newScoreProducerServiceImpl implements newDataProducerService<playerScore> {

    Logger logger = LoggerFactory.getLogger(newScoreProducerServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, playerScore> kafkaTemplate;

    public void addDataToQueue(playerScore newScore) throws MessageQueueFailureException {
        try {
            kafkaTemplate.send(GAMING_CONSTANTS.KAFKA_TOPIC, newScore);
        } catch (Exception e) {
            logger.error("Send message failed - {}", e.getMessage());
            throw new MessageQueueFailureException(e.getMessage());
        }

    }
}
