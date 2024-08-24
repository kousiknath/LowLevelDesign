package com.lld.stockbroker.model;

public class EquityPositionEntry implements Entry {
    private Company company;
    private Integer quantity;
    private Double averagePrice;
    private Double investedAmount;

    // Dynamic data
    private Double absoluteAmountChange;
    private Double absoluteChangePercentage;

    public EquityPositionEntry(Company company, Integer quantity, Double investedAmount) {
        this.company = company;
        this.quantity = quantity;
        this.investedAmount = investedAmount;
    }

    public EquityPositionEntry union(EquityPositionEntry otherEntry) {
        if (otherEntry != null) {
            this.investedAmount += otherEntry.investedAmount;
            this.quantity += otherEntry.quantity;
            this.averagePrice = this.investedAmount / this.quantity;
        }

        return this;
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
                "company=" + getCompany() +
                ", quantity=" + getQuantity() +
                ", averagePrice=" + getAveragePrice() +
                ", investedAmount=" + getTotalInvestedAmount() +
                ", absoluteAmountChange=" + getAbsoluteAmountChange() +
                ", absoluteChangePercentage=" + getPercentageAmountChange() +
                '}';
    }
}
