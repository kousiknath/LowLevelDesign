package com.lld.onlinejudge.service.compiler;

import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.model.TestCase;
import com.lld.onlinejudge.model.TestCaseResult;

import java.util.List;

public interface CompilerService {
    List<TestCaseResult> compile(String code, List<TestCase> testCase) throws CompilationException;
}
