package com.lld.onlinejudge.service;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionMetadata;
import com.lld.uberdriverdispatch.exception.ServiceException;

public interface QuestionMetadataService {
    QuestionMetadata create(Question question) throws ServiceException;
    QuestionMetadata update(QuestionMetadata newMetadata) throws ServiceException;
    QuestionMetadata get(Question question) throws ServiceException;
}
