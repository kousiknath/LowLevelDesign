package com.lld.onlinejudge.service.impl.compiler;

import com.lld.onlinejudge.constant.TestCaseRunStatus;
import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.model.TestCase;
import com.lld.onlinejudge.model.TestCaseResult;
import com.lld.onlinejudge.service.compiler.CompilerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaCompilerServiceImpl implements CompilerService {
    @Override
    public List<TestCaseResult> compile(String code, List<TestCase> testCases) throws CompilationException {
        System.out.println("Java code compiled successfully");

        List<TestCaseResult> testCaseResults = new ArrayList<>();
        for (TestCase testCase: testCases) {
            double random = new Random().nextDouble();
            TestCaseRunStatus runStatus = TestCaseRunStatus.SUCCESS;
            if (random > 0.75) {
                runStatus = TestCaseRunStatus.FAIL;
            }

            TestCaseResult result = new TestCaseResult(testCase, runStatus);
            testCaseResults.add(result);
        }

        return testCaseResults;
    }
}
