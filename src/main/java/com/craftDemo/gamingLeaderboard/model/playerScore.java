package com.craftDemo.gamingLeaderboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="PLAYERSCORE")
public class playerScore implements Comparable<playerScore> {
    @Id
    @Column(name="player_id")
    String playerId;
    Long score;

    public playerScore() {

    }
    public playerScore(String playerId, long score) {
        this.playerId = playerId;
        this.score = score;
    }

    public long getScore() {
        return this.score;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public int compareTo(playerScore p) {
        if (this.score == p.getScore()) {
            return this.playerId.compareTo(p.getPlayerId());
        }
        return Long.compare(this.score, p.getScore());
    }

    @Override
    public String toString() {
        return "{" + playerId + " " + score + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof playerScore competitor)) return false;
        return Objects.equals(this.score, competitor.score) && this.playerId.equals(competitor.playerId);
    }
}

