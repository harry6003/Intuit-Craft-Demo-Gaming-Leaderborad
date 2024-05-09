package com.craftDemo.gamingLeaderboard.service;

import com.craftDemo.gamingLeaderboard.exceptions.CacheInitializationException;
import com.craftDemo.gamingLeaderboard.exceptions.CacheUpdateFailureException;

import java.util.List;

public interface cacheService<T> {
    public void initialize(int topN, List<T> data) throws CacheInitializationException;
    public void addtoCache(T data) throws CacheUpdateFailureException;
    public List<T> getTopNplayers();
}
