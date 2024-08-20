package com.lld.stockbroker.model;

import java.util.List;

public interface Holding {
    Double getInvestedAmount();
    Double getCurrentTotalAmount();
    AmountChange getAbsolutePnL();
    AmountChange getPnLChangePercentage();
    List<Entry> getEntries();
    void addEntry(Entry entry);
}
