package com.lld.invertedindex.index;

import com.lld.invertedindex.document.Document;
import com.lld.invertedindex.analyzer.Token;
import com.lld.invertedindex.analyzer.Analyzer;

import java.util.*;

public class ShardedIndexImpl implements ShardedIndex {
    private int id;
    private Map<Integer, Document> index;
    private Map<String, Set<Integer>> tokens;

    public ShardedIndexImpl(int id) {
        this.id = id;
        this.index = new HashMap<>();
        this.tokens = new HashMap<>();
    }

    @Override
    public void index(Document doc, Analyzer analyzer) {
        Objects.requireNonNull(doc);
        this.index.put(doc.getId(), doc);

        if (analyzer != null) {
            List<Token<String, Integer>> tokens= analyzer.analyze(List.of(doc));
            if (tokens != null) {
                for (Token<String, Integer> token: tokens) {
                    this.tokens.put(token.getKey(), token.getValues());
                }
            }
        }
    }

    @Override
    public List<Document> query(List<String> terms) {
        if (terms == null) {
            throw new IllegalArgumentException("Invalid terms");
        }

        Set<Document> matchedDocuments = new HashSet<>();
        for (String term: terms) {
            Set<Integer> ids = this.tokens.get(term);
            if (ids != null) {
                for (Integer id: ids) {
                    matchedDocuments.add(this.index.get(id));
                }
            }
        }

        return new ArrayList<>(matchedDocuments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShardedIndexImpl that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
