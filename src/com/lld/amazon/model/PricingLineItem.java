package com.lld.amazon.model;

import java.util.Objects;

/*
    A BillingLineItem can represent a granular level
    cost item for a product
 */
public class PricingLineItem {
    private String name;
    // If `amount` is unspecified, `percentageOfBaseAmount`
    private Money amount;
    // will be used
    private Double percentageOfBaseAmount;

    public PricingLineItem(String name, Money amount, Double percentageOfBaseAmount) {
        this.name = name;
        this.amount = amount;
        this.percentageOfBaseAmount = percentageOfBaseAmount;
    }

    public Money getAmount() {
        return amount;
    }

    public Double getPercentageOfBaseAmount() {
        return percentageOfBaseAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, this.amount.getCurrencySymbol().name());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        PricingLineItem o = (PricingLineItem) other;
        return this.name.equals(o.name)
                && this.amount.getCurrencySymbol().name().equals(o.amount.getCurrencySymbol().name());
    }
}
