package com.lld.stockbroker.model;

import com.lld.stockbroker.exception.NotImplementedException;

import java.util.List;

public interface Holding {
    Double getInvestedAmount();
    Double getCurrentTotalAmount();
    AmountChange getAbsolutePnL();
    AmountChange getPnLChangePercentage();
    List<Entry> getEntries();
    void addEntry(Entry entry) throws NotImplementedException;
}
