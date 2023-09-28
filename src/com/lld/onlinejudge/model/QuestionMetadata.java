package com.lld.onlinejudge.model;

public class QuestionMetadata {
    private int submitted;
    private int accepted;
    private Question question;

    public QuestionMetadata(Question question) {
        this.question = question;
    }

    public int getSubmitted() {
        return submitted;
    }

    public int getAccepted() {
        return accepted;
    }

    public double getAcceptanceRate() {
        return accepted * 100.0 / submitted;
    }

    public Question getQuestion() {
        return question;
    }

    public void incrementSubmitted() {
        this.submitted++;
    }

    public void incrementAccepted() {
        this.accepted++;
    }

    @Override
    public String toString() {
        return "Question: " + this.question.toString() + ", accepted: " + accepted + ", submitted: "
                + submitted + ", acceptance rate: " + getAcceptanceRate();
    }
}
