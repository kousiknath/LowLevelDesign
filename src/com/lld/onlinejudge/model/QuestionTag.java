package com.lld.onlinejudge.model;

import com.lld.onlinejudge.constant.DifficultyLevel;

import java.util.List;

public class QuestionTag {
    private Question question;
    private DifficultyLevel difficultyLevel;
    private List<Topic> topics;
    private List<Company> companies;

    public QuestionTag(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}
