package com.lld.leaderboard;

import com.lld.leaderboard.model.LeaderboardEntry;
import com.lld.leaderboard.service.LeaderboardService;
import com.lld.leaderboard.service.impl.LeaderboardServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static void test1() {
        System.out.println("Test 1 ---------------------------");
        LeaderboardEntry<String, Object> entry1 = new LeaderboardEntry<>("key1", "Test Value 1");
        entry1.updateScore(1.0);

        LeaderboardEntry<String, Object> entry2 = new LeaderboardEntry<>("key2", "Test Value 2");
        entry2.updateScore(2.0);

        LeaderboardEntry<String, Object> entry3 = new LeaderboardEntry<>("key3", "Test Value 3");
        entry3.updateScore(2.1);

        LeaderboardService<String, Object> leaderboardService = new LeaderboardServiceImpl<>();
        leaderboardService.addOrUpdateEntry(entry1);
        leaderboardService.addOrUpdateEntry(entry2);
        leaderboardService.addOrUpdateEntry(entry3);

        System.out.println("Top 2 entries ...");
        List<LeaderboardEntry<String, Object>> top10 = leaderboardService.getTopN(2);
        System.out.println(Arrays.deepToString(top10.toArray()));

        System.out.println("Updating score of entry 1");
        entry1.updateScore(10.0);
        leaderboardService.addOrUpdateEntry(entry1);

        System.out.println("Top 2 entries ...");
        top10 = leaderboardService.getTopN(2);
        System.out.println(Arrays.deepToString(top10.toArray()));

        System.out.println("Getting element with key : " + entry2.getKey() + " = " + leaderboardService.getEntry(entry2.getKey()));
        System.out.println("Deleting element with key : " + entry1.getKey());
        leaderboardService.deleteEntry(entry1.getKey());
        System.out.println("Top 2 entries ...");
        top10 = leaderboardService.getTopN(2);
        System.out.println(Arrays.deepToString(top10.toArray()));
    }

    public static void test2() {
        System.out.println("Test 2 -----------------------");
        double[] scores = { 3.4, 2.1, 9.0, 6, 3, 10, 1, 8.3, 4.9, 1};
        LeaderboardService<String, Object> leaderboardService = new LeaderboardServiceImpl<>();

        for (int index = 1; index <= scores.length; index++) {
            LeaderboardEntry<String, Object> entry = new LeaderboardEntry<>("key " + index, "Test Value " + index);
            entry.updateScore(scores[index - 1]);
            leaderboardService.addOrUpdateEntry(entry);
        }

        System.out.println("Top 10 entries ...");
        System.out.println(Arrays.deepToString(leaderboardService.getTopN(10).toArray()));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
