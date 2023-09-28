package com.lld.onlinejudge.model;

import java.util.ArrayList;
import java.util.List;

public class SolutionRunResult {
    private Solution solution;
    private List<TestCaseResult> testCaseResults;

    public SolutionRunResult() {
        this.testCaseResults = new ArrayList<>();
    }

    public SolutionRunResult(Solution solution) {
        this();
        this.solution = solution;
    }

    public void addTestCaseResult(TestCaseResult testCaseResult) {
        this.testCaseResults.add(testCaseResult);
    }

    public void addTestCaseResult(List<TestCaseResult> testCaseResult) {
        this.testCaseResults.addAll(testCaseResult);
    }

    public List<TestCaseResult> getTestCaseResults() {
        return this.testCaseResults;
    }

    public Solution getSolution() {
        return this.solution;
    }
}
