package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.constant.DifficultyLevel;
import com.lld.onlinejudge.model.*;
import com.lld.onlinejudge.repository.QuestionRepository;
import com.lld.onlinejudge.service.QuestionMetadataService;
import com.lld.onlinejudge.service.QuestionService;
import com.lld.onlinejudge.service.QuestionTagService;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {
    private QuestionTagService questionTagService;
    private QuestionMetadataService questionMetadataService;
    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionTagService tagService, QuestionMetadataService metadataService) {
        this.questionTagService = tagService;
        this.questionMetadataService = metadataService;
        this.questionRepository = new QuestionRepository();
    }

    @Override
    public Question create(String title, String description, User author, DifficultyLevel difficultyLevel) throws ServiceException {
        if (title == null || description == null || author == null) {
            throw new ServiceException("Essential parameters can not be null to create question");
        }

        Question question = new Question(title, description, author);
        this.questionRepository.add(question);

        this.questionTagService.create(question, difficultyLevel);
        this.questionMetadataService.create(question);
        return question;
    }

    @Override
    public Question get(String id) {
        return this.questionRepository.get(id);
    }

    @Override
    public List<Question> getQuestionsByAuthor(User author) {
        return this.questionRepository.getBy(author);
    }

    @Override
    public QuestionComment doComment(Question question, String comment, User author) throws ServiceException {
        if (question == null || comment == null || author == null) {
            throw new ServiceException("Invalid arguments");
        }

        QuestionComment questionComment = new QuestionComment(question, comment, author);
        this.questionRepository.addComment(questionComment);
        return questionComment;
    }

    @Override
    public List<QuestionComment> getComments(Question question) {
        return this.questionRepository.getComments(question);
    }

    @Override
    public QuestionDiscussion discuss(Question question, String title, String description, User author) throws ServiceException {
        if (question == null || title == null || description == null || author == null) {
            throw new ServiceException("Invalid arguments");
        }

        QuestionDiscussion questionDiscussion = new QuestionDiscussion(question, title, description, author);
        this.questionRepository.addDiscussion(questionDiscussion);
        return questionDiscussion;
    }

    @Override
    public List<QuestionDiscussion> getDiscussions(Question question) {
        return this.questionRepository.getDiscussions(question);
    }
}
