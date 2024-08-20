package com.lld.stockbroker.model;

public class EquityHoldingEntry implements Entry {
    private Company company;
    private Integer quantity;
    private Double averagePrice;
    private Double investedAmount;

    // Dynamic data
    private Double absoluteAmountChange;
    private Double absoluteChangePercentage;

    public EquityHoldingEntry(Company company, Integer quantity, Double investedAmount) {
        this.company = company;
        this.quantity = quantity;
        this.investedAmount = investedAmount;
    }

    @Override
    public Company getCompany() {
        return this.company;
    }

    @Override
    public Integer getQuantity() {
        return this.quantity;
    }

    @Override
    public Double getAveragePrice() {
        return this.investedAmount / this.quantity;
    }

    @Override
    public Double getTotalInvestedAmount() {
        return this.investedAmount;
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
        return "EquityPortfolioEntry{" +
                "company=" + company +
                ", quantity=" + quantity +
                ", averagePrice=" + averagePrice +
                ", investedAmount=" + investedAmount +
                ", absoluteAmountChange=" + absoluteAmountChange +
                ", absoluteChangePercentage=" + absoluteChangePercentage +
                '}';
    }
}
