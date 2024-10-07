package com.lld.invertedindex.index;

import com.lld.invertedindex.document.Document;

import java.util.Collections;
import java.util.List;

public class MatchImpl implements Match {
    private List<Document> matchedDocuments;
    private int hits;
    private int totalShards;

    public MatchImpl(List<Document> matchedDocuments, int hits, int totalShards) {
        this.matchedDocuments = matchedDocuments;
        this.hits = hits;
        this.totalShards = totalShards;
    }

    @Override
    public int hits() {
        return this.hits;
    }

    @Override
    public double score() {
        return (double) this.hits / this.totalShards;
    }

    @Override
    public List<Document> matchedDocuments() {
        return Collections.unmodifiableList(this.matchedDocuments);
    }

    @Override
    public String toString() {
        return "MatchImpl{" +
                "matchedDocuments=" + matchedDocuments +
                ", hits=" + hits +
                ", totalShards=" + totalShards +
                ", score=" + score() +
                '}';
    }
}
