package com.lld.onlinejudge.repository;

import com.lld.onlinejudge.model.Company;
import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionTag;
import com.lld.onlinejudge.model.Topic;

import java.util.*;

public class QuestionTagRepository {
    private Map<Question, QuestionTag> questionTags;
    private Map<Company, List<Question>> companyToQuestionMap;
    private Map<Topic, List<Question>> topicToQuestionMap;

    public QuestionTagRepository() {
        this.questionTags = new HashMap<>();
        this.companyToQuestionMap = new HashMap<>();
        this.topicToQuestionMap = new HashMap<>();
    }

    public void add(Question question, QuestionTag questionTag) {
        this.questionTags.putIfAbsent(question, questionTag);
    }

    public QuestionTag get(Question question) {
        return this.questionTags.get(question);
    }

    public void addCompanyTags(Question question, List<Company> companies) {
        for (Company company: companies) {
            this.companyToQuestionMap.putIfAbsent(company, new ArrayList<>());
            this.companyToQuestionMap.get(company).add(question);
        }
    }

    public void addTopicTags(Question question, List<Topic> topics) {
        for (Topic topic: topics) {
            this.topicToQuestionMap.putIfAbsent(topic, new ArrayList<>());
            this.topicToQuestionMap.get(topic).add(question);
        }
    }

    public List<Question> getBy(Company company) {
        return this.companyToQuestionMap.getOrDefault(company, Collections.emptyList());
    }

    public List<Question> getBy(Topic topic) {
        return this.topicToQuestionMap.getOrDefault(topic, Collections.emptyList());
    }
}
