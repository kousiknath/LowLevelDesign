package com.lld.stockbroker.model;

import com.lld.stockbroker.constant.OrderType;

import java.util.*;

public class Position {
    private Map<String, Map<OrderType, Entry>> entries; // Symbol -> Map of (Order Type, Cumulative Entry)

    public Position() {
        this.entries = new HashMap<>();
    }

    public void addEntry(String symbol, OrderType orderType, Entry entry) {
        this.entries.putIfAbsent(symbol, new HashMap<>());
        if (this.entries.get(symbol).get(orderType) == null) {
            this.entries.get(symbol).put(orderType, entry);
        } else {
            EquityPositionEntry existingEntry = (EquityPositionEntry) this.entries.get(symbol).get(orderType);
            EquityPositionEntry newEntry = existingEntry.union((EquityPositionEntry) entry);
            this.entries.get(symbol).put(orderType, newEntry);
        }
    }

    static class PrintablePositionEntry {
        private String symbol;
        private OrderType orderType;
        private Entry entry;

        public PrintablePositionEntry(String symbol, OrderType orderType, Entry entry) {
            this.symbol = symbol;
            this.orderType = orderType;
            this.entry = entry;
        }

        @Override
        public String toString() {
            return "PrintablePositionEntry{" +
                    "symbol='" + symbol + '\'' +
                    ", orderType=" + orderType +
                    ", entry=" + entry +
                    '}';
        }
    }

    @Override
    public String toString() {
        List<PrintablePositionEntry> toPrint = new ArrayList<>();

        for (String symbol: this.entries.keySet()) {
            for (OrderType orderType: this.entries.get(symbol).keySet()) {
                PrintablePositionEntry printable =
                        new PrintablePositionEntry(symbol, orderType, this.entries.get(symbol).get(orderType));
                toPrint.add(printable);
            }
        }

        return Arrays.deepToString(toPrint.toArray());
    }
}
