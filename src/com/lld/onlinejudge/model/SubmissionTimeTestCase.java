package com.lld.onlinejudge.model;

public class SubmissionTimeTestCase {
    private String id;
    private Solution solution;
    private TestCase testCase;
    private String actualResult;

    public String getId() {
        return id;
    }

    public Solution getSolution() {
        return solution;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public String getActualResult() {
        return actualResult;
    }
}
