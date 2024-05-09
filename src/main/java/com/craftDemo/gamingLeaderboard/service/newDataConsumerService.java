package com.craftDemo.gamingLeaderboard.service;

public interface newDataConsumerService<T> {
    public void consumeDataFromQueue(T newData);
}
