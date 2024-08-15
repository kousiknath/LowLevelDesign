package com.lld.leaderboard.service;

import com.lld.leaderboard.model.LeaderboardEntry;

import java.util.List;

public interface LeaderboardService<K extends Comparable<K>, V> {
    void addOrUpdateEntry(LeaderboardEntry<K, V> entry);
    LeaderboardEntry<K, V> getEntry(K key);
    LeaderboardEntry<K, V> deleteEntry(K key);
    List<LeaderboardEntry<K, V>> getTopN(int N);
    List<LeaderboardEntry<K, V>> getEntriesBetweenScore(double from, double to);
}
