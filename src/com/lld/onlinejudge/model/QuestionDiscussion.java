package com.lld.onlinejudge.model;

public class QuestionDiscussion {
    private Question question;
    private String title;
    private String description;
    private CommonMetadata metadata;

    public QuestionDiscussion(Question question, String title, String description, User author) {
        this.question = question;
        this.title = title;
        this.description = description;
        this.metadata = new CommonMetadata(author);
    }

    public Question getQuestion() {
        return question;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public CommonMetadata getMetadata() {
        return metadata;
    }
}
