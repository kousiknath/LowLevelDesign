package com.lld.stockbroker.model;

import java.util.Objects;

public class CompanyTicker implements Ticker {
    private String stockSymbol;
    private Money latestPrice;
    private Exchange sourceExchange;
    private Double fiftyTwoWeeksLow;
    private Double getFiftyTwoWeeksHigh;

    public CompanyTicker(String symbol, Exchange exchange) {
        this.stockSymbol = symbol;
        this.sourceExchange = exchange;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public Money getLatestPrice() {
        return latestPrice;
    }

    public Exchange getSourceExchange() {
        return sourceExchange;
    }

    public Double getFiftyTwoWeeksLow() {
        return fiftyTwoWeeksLow;
    }

    public Double getGetFiftyTwoWeeksHigh() {
        return getFiftyTwoWeeksHigh;
    }

    @Override
    public void feedValue(Money amount) {
        this.latestPrice = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyTicker that)) return false;
        return Objects.equals(getStockSymbol(), that.getStockSymbol()) && Objects.equals(getSourceExchange(), that.getSourceExchange());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockSymbol(), getSourceExchange());
    }

    @Override
    public String toString() {
        return "CompanyTicker{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", latestPrice=" + latestPrice +
                ", sourceExchange=" + sourceExchange +
                '}';
    }
}
