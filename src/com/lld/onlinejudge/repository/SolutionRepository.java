package com.lld.onlinejudge.repository;

import com.lld.onlinejudge.model.User;
import com.lld.onlinejudge.model.Question;
import com.lld.onlinejudge.model.Solution;
import com.lld.onlinejudge.model.SolutionComment;

import java.util.*;

public class SolutionRepository {
    private Map<Question, List<Solution>> solutions;
    private Map<User, List<Solution>> solutionsByUser;
    private Map<Solution, List<SolutionComment>> solutionComments;

    public SolutionRepository() {
        this.solutions = new HashMap<>();
        this.solutionComments = new HashMap<>();
        this.solutionsByUser = new HashMap<>();
    }

    public void add(Solution solution) {
        this.solutions.putIfAbsent(solution.getQuestion(), new ArrayList<>());
        this.solutions.get(solution.getQuestion()).add(solution);

        this.solutionsByUser.putIfAbsent(solution.getMetadata().getAuthor(), new ArrayList<>());
        this.solutionsByUser.get(solution.getMetadata().getAuthor()).add(solution);
    }

    public List<Solution> get(Question question) {
        return this.solutions.getOrDefault(question, Collections.emptyList());
    }

    public List<Solution> getBy(User user) {
        return this.solutionsByUser.getOrDefault(user, Collections.emptyList());
    }

    public void addComment(SolutionComment solutionComment) {
        this.solutionComments.putIfAbsent(solutionComment.getSolution(), new ArrayList<>());
        this.solutionComments.get(solutionComment.getSolution()).add(solutionComment);
    }

    public List<SolutionComment> getComments(Solution solution) {
        return this.solutionComments.get(solution);
    }
}
