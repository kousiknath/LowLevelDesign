package com.lld.stockbroker.model;

import com.lld.stockbroker.exception.NotImplementedException;

import java.util.*;

public class AnyHolding implements Holding {
    private Double investedAmount;
    private Double currentAmount;
    private Double absolutePnL;
    private Double absolutePnLChangePercentage;
    private Map<String, Entry> entries;

    public AnyHolding() {
        this.entries = new HashMap<>();
    }

    @Override
    public Double getInvestedAmount() {
        return this.investedAmount;
    }

    @Override
    public Double getCurrentTotalAmount() {
        // To be calculated from the current ticker amount
        return null;
    }

    @Override
    public AmountChange getAbsolutePnL() {
        // To be calculated from the current ticker amount
        return null;
    }

    @Override
    public AmountChange getPnLChangePercentage() {
        // To be calculated from the current ticker amount
        return null;
    }

    @Override
    public List<Entry> getEntries() {
        return this.entries.values().stream().toList();
    }

    @Override
    public void addEntry(Entry entry) throws NotImplementedException {
        String symbol = entry.getCompany().getCompanyTicker().getStockSymbol();
        if (!this.entries.containsKey(symbol)) {
            this.entries.put(symbol, entry);
        } else {
            EquityHoldingEntry existingEntry = (EquityHoldingEntry) this.entries.get(symbol);
            EquityHoldingEntry newEntry = existingEntry.union((EquityHoldingEntry) entry);
            this.entries.put(symbol, newEntry);
        }

        calculateInvestmentData();
    }

    private void calculateInvestmentData() throws NotImplementedException {
        Double investedAmount = 0D;

        for (String symbol: this.entries.keySet()) {
            investedAmount += this.entries.get(symbol).getTotalInvestedAmount();
        }

        this.investedAmount = investedAmount;
    }

    @Override
    public String toString() {
        List<Map.Entry<String, Entry>> printable = new ArrayList<>(this.entries.entrySet());

        return "AnyHolding{" +
                "investedAmount=" + investedAmount +
                ", currentAmount=" + currentAmount +
                ", absolutePnL=" + absolutePnL +
                ", absolutePnLChangePercentage=" + absolutePnLChangePercentage +
                ", entries=" + Arrays.deepToString(printable.toArray()) +
                '}';
    }
}
