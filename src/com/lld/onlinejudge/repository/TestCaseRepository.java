package com.lld.onlinejudge.repository;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.TestCase;

import java.util.*;

public class TestCaseRepository {
    Map<Question, List<TestCase>> testCases;

    public TestCaseRepository() {
        this.testCases = new HashMap<>();
    }

    public void add(TestCase testCase) {
        this.testCases.putIfAbsent(testCase.getQuestion(), new ArrayList<>());
        this.testCases.get(testCase.getQuestion()).add(testCase);
    }

    public List<TestCase> get(Question question) {
        return testCases.getOrDefault(question, Collections.emptyList());
    }
}
