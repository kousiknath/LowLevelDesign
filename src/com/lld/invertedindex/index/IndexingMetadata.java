package com.lld.invertedindex.index;

public class IndexingMetadata {
    private int totalDocuments;
    private int numberOfShards;

    public IndexingMetadata(int docs, int numShards) {
        this.totalDocuments = docs;
        this.numberOfShards = numShards;
    }

    public int getTotalDocuments() {
        return totalDocuments;
    }

    public int getNumberOfShards() {
        return numberOfShards;
    }
}
