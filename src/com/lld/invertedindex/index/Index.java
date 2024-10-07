package com.lld.invertedindex.index;


import com.lld.invertedindex.document.Document;
import com.lld.invertedindex.machine.Machine;
import com.lld.invertedindex.analyzer.Analyzer;

import java.util.List;

public interface Index {
    void addMachine(Range range, Machine machine);
    void removeMachine(Machine machine);
    void index(List<Document> documents, Analyzer analyzer);
    Match query(List<String> terms);
}
