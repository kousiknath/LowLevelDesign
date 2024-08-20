package com.lld.stockbroker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnyHolding implements Holding {
    private Double investedAmount;
    private Double currentAmount;
    private Double absolutePnL;
    private Double absolutePnLChangePercentage;
    private List<Entry> entries;

    public AnyHolding() {
        this.entries = new ArrayList<>();
    }

    @Override
    public Double getInvestedAmount() {
        return null;
    }

    @Override
    public Double getCurrentTotalAmount() {
        return null;
    }

    @Override
    public AmountChange getAbsolutePnL() {
        return null;
    }

    @Override
    public AmountChange getPnLChangePercentage() {
        return null;
    }

    @Override
    public List<Entry> getEntries() {
        return null;
    }

    @Override
    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    @Override
    public String toString() {
        return "AnyHolding{" +
                "investedAmount=" + investedAmount +
                ", currentAmount=" + currentAmount +
                ", absolutePnL=" + absolutePnL +
                ", absolutePnLChangePercentage=" + absolutePnLChangePercentage +
                ", entries=" + Arrays.deepToString(entries.toArray()) +
                '}';
    }
}
