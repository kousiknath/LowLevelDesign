package com.lld.onlinejudge.service;

import com.lld.onlinejudge.constant.DifficultyLevel;
import com.lld.onlinejudge.model.*;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.List;

public interface QuestionService {
    Question create(String title, String description, User author, DifficultyLevel difficultyLevel) throws ServiceException;
    Question get(String id);
    List<Question> getQuestionsByAuthor(User author);

    // Comments and discussions can be implemented in a separate service as well
    // but since their functionalities are limited, we have put it here.
    QuestionComment doComment(Question question, String comment, User author) throws ServiceException;
    List<QuestionComment> getComments(Question question);
    QuestionDiscussion discuss(Question question, String title, String description, User author) throws ServiceException;
    List<QuestionDiscussion> getDiscussions(Question question);
}
