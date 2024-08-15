package com.lld.leaderboard.service.impl;

import com.lld.leaderboard.model.LeaderboardEntry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MaxHeap<K extends Comparable<K>, V> {
    private final LeaderboardEntry<K, V>[] pq;
    // entry to the index in a priority queue array map
    private final ConcurrentHashMap<LeaderboardEntry<K, V>, Integer> map;
    private int N = 0;
    private ReentrantLock lock;

    public MaxHeap(int maxN) {
        this.pq = new LeaderboardEntry[maxN + 1];
        this.map = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock(true);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void addOrUpdate(LeaderboardEntry<K, V> entry) {
        int index;

        try {
            this.lock.lock();

            if (map.containsKey(entry)) {
                index = map.get(entry);
            } else {
                index = ++N;
            }

            this.map.put(entry, index);
            this.pq[index] = entry;
            entry.setRank(index);

            swim(index);
        } finally {
            this.lock.unlock();
        }
    }

    public LeaderboardEntry<K, V> get(K key) {
        LeaderboardEntry<K, V> elementToSearch = new LeaderboardEntry<>(key, null);

        try {
            this.lock.lock();

            if (this.map.containsKey(elementToSearch)) {
                return this.pq[this.map.get(elementToSearch)];
            }
        } finally {
            this.lock.unlock();
        }

        return null;
    }

    public LeaderboardEntry<K, V> delete(K key) {
        LeaderboardEntry<K, V> elementToSearch = new LeaderboardEntry<>(key, null);

        try {
            this.lock.lock();

            if (!this.map.containsKey(elementToSearch)) {
                return null;
            }

            int elementIndex = this.map.get(elementToSearch);
            LeaderboardEntry<K, V> elementToDelete = this.pq[elementIndex];

            int lastIndex = N--;
            LeaderboardEntry<K, V> last = this.pq[lastIndex];
            exchange(elementIndex, lastIndex, false);
            this.map.put(last, elementIndex);

            this.pq[N + 1] = null;
            this.map.remove(elementToDelete);

            sink(elementIndex);
            return elementToDelete;
        } finally {
            this.lock.unlock();
        }
    }

    public LeaderboardEntry<K, V> poll() {
        LeaderboardEntry<K, V> max;

        try {
            this.lock.lock();

            max = this.pq[1];
            int lastIndex = N--;
            LeaderboardEntry<K, V> last = this.pq[lastIndex];
            // We don't track the index of the top entry as we
            // are deleting it. We only track the position of the
            // replacement entry
            exchange(1, lastIndex, false);
            this.map.put(last, 1);

            this.pq[N + 1] = null;
            this.map.remove(max);

            sink(1);
        } finally {
            this.lock.unlock();
        }
        return max;
    }

    private void swim(int k) {
        try {
            this.lock.lock();

            while (k > 1 && less(k / 2, k)) {
                exchange(k / 2, k, true);
                k = k / 2;
            }
        } finally {
            this.lock.unlock();
        }
    }

    private void sink(int k) {
        try {
            this.lock.lock();

            while (2 * k <= N) {
                int j = 2 * k;
                if (j < N && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                exchange(k, j, true);
                k = j;
            }
        } finally {
            this.lock.unlock();
        }
    }

    private boolean less(int i, int j) {
        try {
            this.lock.lock();
            return pq[i].getScore().compareTo(pq[j].getScore()) < 0;
        } finally {
            this.lock.unlock();
        }
    }

    private void exchange(int i, int j, boolean trackEntryIndex) {
        try {
            this.lock.lock();

            LeaderboardEntry<K, V> first = this.pq[i];
            LeaderboardEntry<K, V> second = this.pq[j];

            LeaderboardEntry<K, V> temp = this.pq[i];
            this.pq[i] = this.pq[j];
            this.pq[i].setRank(j);
            this.pq[j] = temp;
            this.pq[j].setRank(i);

            if (trackEntryIndex) {
                this.map.put(first, j);
                this.map.put(second, i);
            }
        } finally {
            this.lock.unlock();
        }
    }
}
