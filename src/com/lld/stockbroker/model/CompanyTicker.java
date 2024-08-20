package com.lld.stockbroker.model;

public class CompanyTicker implements Ticker {
    private String stockSymbol;
    private Money latestPrice;
    private Exchange sourceExchange;
    private Double fiftyTwoWeeksLow;
    private Double getFiftyTwoWeeksHigh;

    public CompanyTicker(String symbol) {
        this.stockSymbol = symbol;
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
    public String toString() {
        return "CompanyTicker{" +
                "stockSymbol='" + stockSymbol + '\'' +
                ", latestPrice=" + latestPrice +
                ", sourceExchange=" + sourceExchange +
                '}';
    }
}
