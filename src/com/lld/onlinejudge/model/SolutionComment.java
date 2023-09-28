package com.lld.onlinejudge.model;

public class SolutionComment {
    private Solution solution;
    private String comment;
    private CommonMetadata metadata;

    public SolutionComment(Solution solution, String comment) {
        this.solution = solution;
        this.comment = comment;
    }

    public Solution getSolution() {
        return solution;
    }

    public String getComment() {
        return comment;
    }

    public CommonMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return this.solution.toString() + ", comment: " + comment;
    }
}
