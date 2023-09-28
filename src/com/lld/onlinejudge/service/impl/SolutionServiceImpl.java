package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.constant.SolutionStatus;
import com.lld.onlinejudge.constant.SupportedLanguage;
import com.lld.onlinejudge.constant.TestCaseRunStatus;
import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.exception.NoCompilerFoundException;
import com.lld.onlinejudge.factory.CompilerServiceSimpleFactory;
import com.lld.onlinejudge.model.*;
import com.lld.onlinejudge.repository.SolutionRepository;
import com.lld.onlinejudge.service.BlobService;
import com.lld.onlinejudge.service.QuestionMetadataService;
import com.lld.onlinejudge.service.SolutionService;
import com.lld.onlinejudge.service.TestCaseService;
import com.lld.onlinejudge.service.compiler.CompilerService;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;
import java.util.Random;

public class SolutionServiceImpl implements SolutionService {
    private TestCaseService testCaseService;
    private SolutionRepository solutionRepository;
    private BlobService blobService;
    private QuestionMetadataService questionMetadataService;
    public SolutionServiceImpl(TestCaseService testCaseService, SolutionRepository solutionRepository,
                               QuestionMetadataService questionMetadataService) {
        this.testCaseService = testCaseService;
        this.solutionRepository = solutionRepository;
        this.blobService = new BlobServiceImpl();
        this.questionMetadataService = questionMetadataService;
    }

    @Override
    public SolutionRunResult run(Question question, SupportedLanguage language, String solutionCode)
            throws NoCompilerFoundException, CompilationException, ServiceException {
        if (question == null || language == null || solutionCode == null) {
            throw new ServiceException("Invalid arguments");
        }

        CompilerService compilerService = CompilerServiceSimpleFactory.getCompilerService(language);
        List<TestCase> testCases = testCaseService.get(question);

        List<TestCaseResult> testCaseResults = compilerService.compile(solutionCode, testCases);

        SolutionRunResult solutionRunResult = new SolutionRunResult();
        solutionRunResult.addTestCaseResult(testCaseResults);
        return solutionRunResult;
    }

    @Override
    public SolutionSubmittedResult submit(Question question, User author, SupportedLanguage language, String solutionCode)
            throws NoCompilerFoundException, CompilationException, ServiceException {
        if (question == null || author == null || language == null || solutionCode == null) {
            throw new ServiceException("Invalid arguments");
        }

        SolutionRunResult runResult = run(question, language, solutionCode);

        String codePath = blobService.saveData(solutionCode);
        SolutionStatus solutionStatus = determineSolutionStatus(runResult.getTestCaseResults());

        Solution solution = new Solution(author, question, runResult.getTestCaseResults().size(), language, solutionStatus);
        solution.setSolutionPath(codePath);

        this.solutionRepository.add(solution);

        QuestionMetadata questionMetadata = this.questionMetadataService.get(question);
        questionMetadata.incrementSubmitted();

        if (solutionStatus == SolutionStatus.ACCEPTED) {
            questionMetadata.incrementAccepted();
        }

        this.questionMetadataService.update(questionMetadata);

        return new SolutionSubmittedResult(solution,
                solutionStatus, solutionStatus == SolutionStatus.ACCEPTED ? new Random().nextInt(1000) : -1);
    }

    @Override
    public SolutionComment doComment(Solution solution, String comment) throws ServiceException {
        if (solution == null || comment == null) {
            throw new ServiceException("Invalid arguments");
        }

        SolutionComment solutionComment = new SolutionComment(solution, comment);
        this.solutionRepository.addComment(solutionComment);
        return solutionComment;
    }

    @Override
    public List<SolutionComment> getComments(Solution solution) {
        return solutionRepository.getComments(solution);
    }

    @Override
    public List<Solution> getSolutionsByAuthor(User author) {
        return this.solutionRepository.getBy(author);
    }

    private SolutionStatus determineSolutionStatus(List<TestCaseResult> testCaseResults) {
        for (TestCaseResult result: testCaseResults) {
            if (result.getTestCaseRunStatus() == TestCaseRunStatus.FAIL) {
                return SolutionStatus.FAILED;
            }
        }

        return SolutionStatus.ACCEPTED;
    }
}
