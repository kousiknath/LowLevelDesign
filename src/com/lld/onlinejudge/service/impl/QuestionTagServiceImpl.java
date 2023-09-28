package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.constant.DifficultyLevel;
import com.lld.onlinejudge.model.Company;
import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionTag;
import com.lld.onlinejudge.model.Topic;
import com.lld.onlinejudge.repository.QuestionTagRepository;
import com.lld.onlinejudge.service.QuestionTagService;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public class QuestionTagServiceImpl implements QuestionTagService {
    private QuestionTagRepository questionTagRepository;
    public QuestionTagServiceImpl() {
        this.questionTagRepository = new QuestionTagRepository();
    }

    @Override
    public QuestionTag create(Question question, DifficultyLevel difficultyLevel) throws ServiceException {
        if (question == null) {
            throw new ServiceException("Invalid arguments");
        }

        QuestionTag tag = getOrCreate(question);
        tag.setDifficultyLevel(difficultyLevel);
        return tag;
    }

    private QuestionTag getOrCreate(Question question) {
        QuestionTag questionTag = questionTagRepository.get(question);
        if (questionTag != null) {
            return questionTag;
        }

        questionTag = new QuestionTag(question);
        questionTagRepository.add(question, questionTag);
        return questionTag;
    }

    @Override
    public QuestionTag get(Question question) throws ServiceException {
        return this.questionTagRepository.get(question);
    }

    @Override
    public QuestionTag tagWith(Question question, DifficultyLevel difficultyLevel, List<Topic> topics, List<Company> companies) throws ServiceException {
        if (question == null) {
            throw new ServiceException("Invalid arguments");
        }

        QuestionTag tag = this.getOrCreate(question);

        if (difficultyLevel != null) {
            tag.setDifficultyLevel(difficultyLevel);
        }

        if (topics != null) {
            tag.setTopics(topics);
            this.questionTagRepository.addTopicTags(question,  topics);
        }

        if (companies != null) {
            tag.setCompanies(companies);
            this.questionTagRepository.addCompanyTags(question,  companies);
        }

        return tag;
    }

    @Override
    public List<Question> getTaggedQuestionsBy(Company company) {
        return this.questionTagRepository.getBy(company);
    }

    @Override
    public List<Question> getTaggedQuestionsBy(Topic topic) {
        return this.questionTagRepository.getBy(topic);
    }
}
