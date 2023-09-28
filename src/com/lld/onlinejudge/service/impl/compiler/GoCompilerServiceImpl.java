package com.lld.onlinejudge.service.impl.compiler;

import com.lld.onlinejudge.constant.TestCaseRunStatus;
import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.model.TestCase;
import com.lld.onlinejudge.model.TestCaseResult;
import com.lld.onlinejudge.service.compiler.CompilerService;

import java.util.ArrayList;
import java.util.List;

public class GoCompilerServiceImpl implements CompilerService {
    @Override
    public List<TestCaseResult> compile(String code, List<TestCase> testCases) throws CompilationException {
        System.out.println("Go code compiled successfully");

        List<TestCaseResult> testCaseResults = new ArrayList<>();
        for (TestCase testCase: testCases) {
            TestCaseResult result = new TestCaseResult(testCase, TestCaseRunStatus.SUCCESS);
            testCaseResults.add(result);
        }

        return testCaseResults;
    }
}
