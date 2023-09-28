package com.lld.onlinejudge.service.impl;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionMetadata;
import com.lld.onlinejudge.repository.QuestionMetadataRepository;
import com.lld.onlinejudge.service.QuestionMetadataService;
import com.lld.uberdriverdispatch.exception.ServiceException;

public class QuestionMetadataServiceImpl implements QuestionMetadataService {
    private QuestionMetadataRepository metadataRepository;

    public QuestionMetadataServiceImpl() {
        this.metadataRepository = new QuestionMetadataRepository();
    }

    @Override
    public QuestionMetadata create(Question question) throws ServiceException {
        if (question == null) {
            throw new ServiceException("Invalid question");
        }

        QuestionMetadata metadata = new QuestionMetadata(question);
        this.metadataRepository.add(question, metadata);
        return metadata;
    }

    @Override
    public QuestionMetadata update(QuestionMetadata newMetadata) throws ServiceException {
        if (newMetadata == null) {
            throw new ServiceException("Metadata can not be null");
        }

        QuestionMetadata existingMetadata = get(newMetadata.getQuestion());
        if (existingMetadata == null) {
            throw new ServiceException("Question metadata not found");
        }

        this.metadataRepository.add(newMetadata.getQuestion(), newMetadata);
        return this.metadataRepository.get(newMetadata.getQuestion());
    }

    @Override
    public QuestionMetadata get(Question question) throws ServiceException {
        if (question == null) {
            throw new ServiceException("Invalid question");
        }

        return this.metadataRepository.get(question);
    }
}
