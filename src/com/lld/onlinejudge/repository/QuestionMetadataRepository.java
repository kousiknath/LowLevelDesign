package com.lld.onlinejudge.repository;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionMetadata;

import java.util.HashMap;
import java.util.Map;

public class QuestionMetadataRepository {
    private Map<Question, QuestionMetadata> questionMetadata;

    public QuestionMetadataRepository() {
        this.questionMetadata = new HashMap<>();
    }

    public void add(Question question, QuestionMetadata questionMetadata) {
        this.questionMetadata.putIfAbsent(question, questionMetadata);
    }

    public QuestionMetadata get(Question question) {
        return this.questionMetadata.get(question);
    }
}
