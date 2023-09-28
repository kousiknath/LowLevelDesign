package com.lld.onlinejudge.model;

import com.lld.onlinejudge.constant.TestCaseRunStatus;

public class TestCaseResult {
    private TestCase testCase;
    private TestCaseRunStatus testCaseRunStatus;

    public TestCaseResult(TestCase testCase, TestCaseRunStatus testCaseRunStatus) {
        this.testCase = testCase;
        this.testCaseRunStatus = testCaseRunStatus;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public TestCaseRunStatus getTestCaseRunStatus() {
        return testCaseRunStatus;
    }

    @Override
    public String toString() {
        return "Test case: " + testCase.toString() + ", run status: " + testCaseRunStatus;
    }
}
