package com.lld.leaderboard.service.impl;

import com.lld.leaderboard.model.LeaderboardEntry;
import com.lld.leaderboard.service.LeaderboardService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LeaderboardServiceImpl<K extends Comparable<K>, V> implements LeaderboardService<K, V> {
    private final MaxHeap<K, V> maxHeap;
    private ReentrantLock lock;

    public LeaderboardServiceImpl() {
        this.maxHeap = new MaxHeap<>(100);
        this.lock = new ReentrantLock();
    }
    @Override
    public void addOrUpdateEntry(LeaderboardEntry<K, V> entry) {
        this.maxHeap.addOrUpdate(entry);
    }

    @Override
    public LeaderboardEntry<K, V> getEntry(K key) {
        return this.maxHeap.get(key);
    }

    @Override
    public LeaderboardEntry<K, V> deleteEntry(K key) {
        return this.maxHeap.delete(key);
    }

    @Override
    public List<LeaderboardEntry<K, V>> getTopN(int N) {
        List<LeaderboardEntry<K, V>> result = new ArrayList<>();

        try {
            this.lock.lock();

            while (N > 0 && !this.maxHeap.isEmpty()) {
                result.add(this.maxHeap.poll());
                N--;
            }

            for (LeaderboardEntry<K, V> kvLeaderboardEntry : result) {
                this.maxHeap.addOrUpdate(kvLeaderboardEntry);
            }
        } finally {
            this.lock.unlock();
        }

        return Collections.unmodifiableList(result);
    }

    @Override
    public List<LeaderboardEntry<K, V>> getEntriesBetweenScore(double from, double to) {
        // TODO
        return null;
    }
}
