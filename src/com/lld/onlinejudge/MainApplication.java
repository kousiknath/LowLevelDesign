package com.lld.onlinejudge;

import com.lld.onlinejudge.constant.DifficultyLevel;
import com.lld.onlinejudge.constant.SupportedLanguage;
import com.lld.onlinejudge.exception.CompilationException;
import com.lld.onlinejudge.exception.NoCompilerFoundException;
import com.lld.onlinejudge.model.*;
import com.lld.onlinejudge.repository.SolutionRepository;
import com.lld.onlinejudge.repository.TestCaseRepository;
import com.lld.onlinejudge.service.*;
import com.lld.onlinejudge.service.impl.*;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.Arrays;
import java.util.List;

public class MainApplication {

    private static void test1() throws CompilationException, NoCompilerFoundException, ServiceException {
        QuestionTagService questionTagService = new QuestionTagServiceImpl();
        QuestionMetadataService questionMetadataService = new QuestionMetadataServiceImpl();
        User user1 = new User("user1", "user1@test.com");
        User user2 = new User("user2", "user2@gmail.com");
        User user3 = new User("user3", "user3@gmail.com");

        Topic topic1 = new Topic("algorithms");
        Topic topic2 = new Topic("data structures");

        Company company1 = new Company("company-1");
        Company company2 = new Company("company-2");

        QuestionService questionService = new QuestionServiceImpl(questionTagService, questionMetadataService);
        Question question = questionService.create("question 1", "question 1 blah blah",
                user1, DifficultyLevel.EASY);
        Question retrievedQuestion = questionService.get(question.getId());
        System.out.println(retrievedQuestion);

        questionTagService.tagWith(question, null, List.of(topic1, topic2), List.of(company1, company2));

        List<Question> questionsByTopic = questionTagService.getTaggedQuestionsBy(topic2);
        System.out.println("Questions for topic: " + topic2.getName() + " : " + Arrays.deepToString(questionsByTopic.toArray()));

        List<Question> questionsByCompany = questionTagService.getTaggedQuestionsBy(company1);
        System.out.println("Questions for company: " + company1.getName() + " : " + Arrays.deepToString(questionsByCompany.toArray()));

        List<Question> questionsByUser1 = questionService.getQuestionsByAuthor(user1);
        System.out.println("Questions by author: " + user1.getName() + " : " + Arrays.deepToString(questionsByUser1.toArray()));

        questionService.doComment(question, "comment 1", user1);
        questionService.doComment(question, "comment 2", user2);
        List<QuestionComment> comments = questionService.getComments(question);
        System.out.println("Comments: " + Arrays.deepToString(comments.toArray()));

        TestCaseRepository testCaseRepository = new TestCaseRepository();
        TestCaseService testCaseService = new TestCaseServiceImpl(testCaseRepository);
        TestCase testCase1 = testCaseService.create(question, "input 1", "expected result 1");
        TestCase testCase2 = testCaseService.create(question, "input 2", "expected result 2");
        List<TestCase> testCases = testCaseService.get(question);
        System.out.println("Test cases: " + Arrays.deepToString(testCases.toArray()));

        SolutionRepository solutionRepository = new SolutionRepository();
        SolutionService solutionService = new SolutionServiceImpl(testCaseService, solutionRepository, questionMetadataService);
        SolutionRunResult runResult = solutionService.run(question, SupportedLanguage.JAVA, "some code blah blah");
        System.out.println("Solution run result: " + Arrays.deepToString(runResult.getTestCaseResults().toArray(new TestCaseResult[0])));

        SolutionSubmittedResult solutionSubmittedResult1 = solutionService.submit(question, user2, SupportedLanguage.JAVA, "some code blah blah");
        System.out.println("Solution status: " + solutionSubmittedResult1.getSolutionStatus());

        SolutionSubmittedResult solutionSubmittedResult2 = solutionService.submit(question, user3, SupportedLanguage.JAVA, "some more code blah blah");
        System.out.println("Solution status: " + solutionSubmittedResult2.getSolutionStatus());

        Solution solution = solutionSubmittedResult1.getSolution();
        solutionService.doComment(solution, "comment 1 on solution");
        solutionService.doComment(solution, "comment 2 on solution");
        solutionService.doComment(solution, "comment 3 on solution");

        List<Solution> solutionsByUser1 = solutionService.getSolutionsByAuthor(user1);
        System.out.println("Solutions by user : " + user1.getName() + " : " + Arrays.deepToString(solutionsByUser1.toArray())); // Should be empty

        List<Solution> solutionsByUser2 = solutionService.getSolutionsByAuthor(user2);
        System.out.println("Solutions by user : " + user2.getName() + " : " + Arrays.deepToString(solutionsByUser2.toArray()));

        List<SolutionComment> solutionComments = solutionService.getComments(solution);
        System.out.println(Arrays.deepToString(solutionComments.toArray()));

        QuestionMetadata metadata = questionMetadataService.get(question);
        System.out.println("Question metadata: " + metadata);
    }

    public static void main(String[] args) throws CompilationException, NoCompilerFoundException, ServiceException {
        test1();
    }
}
