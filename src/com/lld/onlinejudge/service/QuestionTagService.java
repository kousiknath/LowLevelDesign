package com.lld.onlinejudge.service;

import com.lld.onlinejudge.constant.DifficultyLevel;
import com.lld.onlinejudge.model.Company;
import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionTag;
import com.lld.onlinejudge.model.Topic;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public interface QuestionTagService {
    QuestionTag create(Question question, DifficultyLevel difficultyLevel) throws ServiceException;
    QuestionTag get(Question question) throws ServiceException;
    QuestionTag tagWith(Question question, DifficultyLevel difficultyLevel, List<Topic> topics, List<Company> companies) throws ServiceException;
    List<Question> getTaggedQuestionsBy(Company company);
    List<Question> getTaggedQuestionsBy(Topic topic);
}
