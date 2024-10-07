package com.lld.invertedindex.index;

import com.lld.invertedindex.document.Document;

import java.util.List;

public interface Match {
    int hits();
    double score();
    List<Document> matchedDocuments();
}
