package com.craftDemo.gamingLeaderboard.config;

import com.craftDemo.gamingLeaderboard.constants.GAMING_CONSTANTS;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class kafkaConfig {
    @Bean
    public NewTopic topic() {
        return new NewTopic(GAMING_CONSTANTS.KAFKA_TOPIC, 1, (short) 1);
    }
}
