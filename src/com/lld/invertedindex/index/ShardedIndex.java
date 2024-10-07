package com.lld.invertedindex.index;


import com.lld.invertedindex.document.Document;
import com.lld.invertedindex.analyzer.Analyzer;

import java.util.List;

interface ShardedIndex {
    void index(Document doc, Analyzer analyzer);
    List<Document> query(List<String> terms);
}
