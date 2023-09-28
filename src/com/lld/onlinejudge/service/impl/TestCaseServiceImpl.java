package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.TestCase;
import com.lld.onlinejudge.repository.TestCaseRepository;
import com.lld.onlinejudge.service.TestCaseService;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public class TestCaseServiceImpl implements TestCaseService {
    private TestCaseRepository testCaseRepository;

    public TestCaseServiceImpl(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public TestCase create(Question question, String input, String expectedResult) throws ServiceException {
        if (question == null || input == null || expectedResult == null) {
            throw new ServiceException("Invalid arguments");
        }

        TestCase testCase = new TestCase(question, input, expectedResult);
        testCaseRepository.add(testCase);
        return testCase;
    }

    @Override
    public List<TestCase> get(Question question) {
        return testCaseRepository.get(question);
    }
}
