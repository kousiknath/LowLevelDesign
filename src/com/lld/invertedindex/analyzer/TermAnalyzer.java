package com.lld.invertedindex.analyzer;

import com.lld.invertedindex.document.Document;

import java.util.*;

public class TermAnalyzer implements Analyzer {
    @Override
    public List<Token<String, Integer>> analyze(List<Document> documents) {
        Map<String, Set<Integer>> map = new HashMap<>();

        for (Document doc: documents) {
            String content = doc.getContent();
            String[] tokens = content.split("\\s+");

            for (String token: tokens) {
                map.putIfAbsent(token, new HashSet<>());
                map.get(token).add(doc.getId());
            }
        }

        List<Token<String, Integer>> analyzedTokens = new ArrayList<>();
        for (String key: map.keySet()) {
            analyzedTokens.add(new Token<>(key, map.get(key)));
        }

        return analyzedTokens;
    }
}
