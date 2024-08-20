package com.lld.stockbroker.model;

import com.lld.stockbroker.exception.NotImplementedException;

public class EquityWatchListEntry implements Entry {
    private Company company;
    private Double absoluteChangeFromYesterdayMarketClose;
    private Double changePercentageFromYesterdayMarketClose;

    public EquityWatchListEntry(Company company) {
        this.company = company;
    }

    @Override
    public Company getCompany() {
        return null;
    }

    @Override
    public Integer getQuantity() throws NotImplementedException {
        throw new NotImplementedException("Not implemented !");
    }

    @Override
    public Double getAveragePrice() throws NotImplementedException {
        throw new NotImplementedException("Not implemented !");
    }

    @Override
    public Double getTotalInvestedAmount() throws NotImplementedException {
        throw new NotImplementedException("Not implemented !");
    }

    @Override
    public AmountChange getAbsoluteAmountChange() {
        return null;
    }

    @Override
    public AmountChange getPercentageAmountChange() {
        return null;
    }

    @Override
    public String toString() {
        return "EquityWatchListEntry{" +
                "company=" + company +
                ", absoluteChangeFromYesterdayMarketClose=" + absoluteChangeFromYesterdayMarketClose +
                ", changePercentageFromYesterdayMarketClose=" + changePercentageFromYesterdayMarketClose +
                '}';
    }
}
