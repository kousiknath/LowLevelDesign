package com.lld.onlinejudge.repository;

import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.QuestionComment;
import com.lld.onlinejudge.model.QuestionDiscussion;
import com.lld.onlinejudge.model.User;

import java.util.*;

public class QuestionRepository {
    private Map<String, Question> questions;
    private Map<Question, List<QuestionComment>> questionComments;
    private Map<Question, List<QuestionDiscussion>> questionDiscussions;
    private Map<User, List<Question>> questionsByAuthor;

    public QuestionRepository() {
        this.questions = new HashMap<>();
        this.questionComments = new HashMap<>();
        this.questionDiscussions = new HashMap<>();
        this.questionsByAuthor = new HashMap<>();
    }

    public void add(Question question) {
        this.questions.put(question.getId(), question);

        this.questionsByAuthor.putIfAbsent(question.getMetadata().getAuthor(), new ArrayList<>());
        this.questionsByAuthor.get(question.getMetadata().getAuthor()).add(question);
    }

    public Question get(String id) {
        return this.questions.get(id);
    }

    public List<Question> getBy(User user) {
        return this.questionsByAuthor.getOrDefault(user, Collections.emptyList());
    }

    public void addComment(QuestionComment questionComment) {
        this.questionComments.putIfAbsent(questionComment.getQuestion(), new ArrayList<>());
        this.questionComments.get(questionComment.getQuestion()).add(questionComment);
    }

    public List<QuestionComment> getComments(Question question) {
        return this.questionComments.getOrDefault(question, Collections.emptyList());
    }

    public void addDiscussion(QuestionDiscussion questionDiscussion) {
        this.questionDiscussions.putIfAbsent(questionDiscussion.getQuestion(), new ArrayList<>());
        this.questionDiscussions.get(questionDiscussion.getQuestion()).add(questionDiscussion);
    }

    public List<QuestionDiscussion> getDiscussions(Question question) {
        return this.questionDiscussions.getOrDefault(question, Collections.emptyList());
    }
}
