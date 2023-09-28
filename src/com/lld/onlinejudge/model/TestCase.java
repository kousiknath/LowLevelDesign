package com.lld.onlinejudge.model;

import java.util.UUID;

public class TestCase {
    private String id;
    private Question question;
    private String input;
    private String expectedResult;

    public TestCase(Question question, String input, String expectedResult) {
        this.id = UUID.randomUUID().toString();
        this.question = question;
        this.input = input;
        this.expectedResult = expectedResult;
    }

    public Question getQuestion() {
        return question;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "Input: " + input + ", expected result: " + expectedResult;
    }
}
