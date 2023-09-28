package com.lld.onlinejudge.model;

import com.lld.onlinejudge.constant.SolutionStatus;

public class SolutionSubmittedResult extends SolutionRunResult {
    private SolutionStatus solutionStatus;
    private int relativeRank;

    public SolutionSubmittedResult(Solution solution, SolutionStatus solutionStatus, int relativeRank) {
        super(solution);
        this.solutionStatus = solutionStatus;
        this.relativeRank = relativeRank;
    }

    public SolutionStatus getSolutionStatus() {
        return solutionStatus;
    }

    public int getRelativeRank() {
        return relativeRank;
    }
}
