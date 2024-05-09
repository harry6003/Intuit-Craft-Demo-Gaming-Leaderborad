package com.craftDemo.gamingLeaderboard.repository;

import com.craftDemo.gamingLeaderboard.model.playerScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface playerScoreRepository extends JpaRepository<playerScore, String> {

}
