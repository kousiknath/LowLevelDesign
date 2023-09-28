package com.lld.onlinejudge.model;

public class QuestionComment {
    private Question question;
    private String comment;
    private CommonMetadata metadata;

    public QuestionComment(Question question, String comment, User author) {
        this.question = question;
        this.comment = comment;
        this.metadata = new CommonMetadata(author);
    }

    public Question getQuestion() {
        return question;
    }

    public String getComment() {
        return comment;
    }

    public CommonMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "Question: " + this.question.toString() + ", comment: " + comment;
    }
}
