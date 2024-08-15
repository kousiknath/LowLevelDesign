package com.lld.leaderboard.model;

import java.util.Objects;

public class LeaderboardEntry<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Double score;
    // TODO: Check if the assignment of rank is correct
    private int rank;

    public LeaderboardEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Double getScore() {
        return score;
    }

    public void updateScore(double newScore) {
        this.score = newScore;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeaderboardEntry<?, ?> entry)) return false;
        return Objects.equals(getKey(), entry.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }

    @Override
    public String toString() {
        return "LeaderboardEntry{" +
                "key=" + key +
                ", value=" + value +
                ", score=" + score +
                ", rank=" + rank +
                '}';
    }
}
