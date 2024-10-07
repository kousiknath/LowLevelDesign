package com.lld.invertedindex.analyzer;

import com.lld.invertedindex.document.Document;

import java.util.List;

public interface Analyzer {
    List<Token<String, Integer>> analyze(List<Document> document);
}
