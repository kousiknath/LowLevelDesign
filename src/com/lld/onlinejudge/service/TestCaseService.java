package com.lld.onlinejudge.service;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.TestCase;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public interface TestCaseService {
    TestCase create(Question question, String input, String expectedResult) throws ServiceException;
    List<TestCase> get(Question question);
}
