package com.lld.invertedindex.index;


import com.lld.invertedindex.analyzer.Analyzer;
import com.lld.invertedindex.document.Document;
import com.lld.invertedindex.machine.Machine;

import java.util.*;

public class DocumentIdRangeBasedIndex implements Index {
    private TreeMap<Range, ShardedIndex> shardedIndexes; // Range to ShardedIndex map
    private IndexingMetadata indexingMetadata;

    public DocumentIdRangeBasedIndex(IndexingMetadata indexingMetadata) {
        validateMetadata(indexingMetadata);

        this.shardedIndexes = new TreeMap<>();
        this.indexingMetadata = indexingMetadata;

        createShards();
    }

    private void validateMetadata(IndexingMetadata indexingMetadata) {
        if (indexingMetadata.getTotalDocuments() <= 0) {
            throw new IllegalArgumentException("Invalid Document Metadata!");
        }

        if (indexingMetadata.getNumberOfShards() <= 0) {
            throw new IllegalArgumentException("At least one shard is required!");
        }
    }

    private void createShards() {
        int numShards = this.indexingMetadata.getNumberOfShards();
        int totalDocs = this.indexingMetadata.getTotalDocuments();

        int numDocsInAShard = totalDocs / numShards;
        int extraDocsInTheLastShard = totalDocs % numShards;

        int startId = 0;
        for (int shardId = 0; shardId < numShards; shardId++) {
            ShardedIndex shardedIndex = new ShardedIndexImpl(shardId);
            Range range = new Range(startId, startId + numDocsInAShard);

            if (shardId == numShards - 1) {
                range = new Range(startId, startId + numDocsInAShard + extraDocsInTheLastShard);
            }

            this.shardedIndexes.put(range, shardedIndex);
            startId += numDocsInAShard;
        }
    }

    @Override
    public void addMachine(Range range, Machine machine) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void removeMachine(Machine machine) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void index(List<Document> documents, Analyzer analyzer) {
        if (documents == null) {
            throw new IllegalArgumentException("Invalid documents");
        }

        // If analyzer is null or absent, apply a default analyzer
        // For now, throw error in case of null analyzer
        if (analyzer == null) {
            throw new IllegalArgumentException("Invalid Analyzer");
        }

        for (Document doc: documents) {
            Map.Entry<Range, ShardedIndex> range = this.shardedIndexes.floorEntry(new Range(doc.getId(), -1));
            range.getValue().index(doc, analyzer);
        }
    }

    @Override
    public Match query(List<String> terms) {
        // Union query (OR)
        // Documents which contain the terms in either or fashion would appear in the result
        // Do a fan-out query
        List<Document> docs = new ArrayList<>();
        List<ShardedIndex> shards = new ArrayList<>(this.shardedIndexes.values());
        int hits = 0;

        for (ShardedIndex shard: shards) {
            List<Document> localResult = shard.query(terms);
            if (!localResult.isEmpty()) {
                hits++;
                docs.addAll(localResult);
            }
        }

        return new MatchImpl(docs, hits, shards.size());
    }
}
