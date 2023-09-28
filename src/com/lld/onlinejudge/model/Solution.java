package com.lld.onlinejudge.model;

import com.lld.onlinejudge.constant.SolutionStatus;
import com.lld.onlinejudge.constant.SupportedLanguage;

import java.util.UUID;

public class Solution {
    private String id;
    private String solutionPath; // Solution can be stored as file / blob in blob storage since they can be bigger
    private Question question;
    private int testCasesPassedCount;
    private SupportedLanguage language;
    private SolutionStatus status;
    private CommonMetadata metadata;

    public Solution(User author, Question question, int testCasesPassedCount, SupportedLanguage language, SolutionStatus status) {
        this.id = UUID.randomUUID().toString();
        // create the solutionPath
        this.question = question;
        this.testCasesPassedCount = testCasesPassedCount;
        this.language = language;
        this.metadata = new CommonMetadata(author);
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getSolutionPath() {
        return solutionPath;
    }

    public void setSolutionPath(String path) {
        this.solutionPath = path;
    }

    public Question getQuestion() {
        return question;
    }

    public int getTestCasesPassedCount() {
        return testCasesPassedCount;
    }

    public SupportedLanguage getLanguage() {
        return language;
    }

    public SolutionStatus getStatus() {
        return status;
    }

    public CommonMetadata getMetadata() {
        return metadata;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.id.equals(((Solution) o).id);
    }

    @Override
    public String toString() {
        return "Solution id: " + this.id + " in language: " + this.language;
    }
}
