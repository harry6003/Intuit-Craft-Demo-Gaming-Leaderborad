package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.MessageQueueFailureException;

public interface newDataProducerService<T> {
    public void addDataToQueue(T newData) throws MessageQueueFailureException;
}
