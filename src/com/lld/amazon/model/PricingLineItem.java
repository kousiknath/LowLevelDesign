package com.lld.amazon.model;

import com.lld.amazon.constant.PricingLineItemType;

import java.util.Objects;

/*
    A BillingLineItem can represent a granular level
    cost item for a product
 */
public class PricingLineItem {
    private String name;
    // If `amount` is specified, it will be directly used
    // for computation
    private Double amount;
    // If `amount` is unspecified, `percentageOfBaseAmount`
    // will be used
    private Double percentageOfBaseAmount;
    private String currencySymbol;

    public PricingLineItem(String name, Double amount, String currencySymbol, Double percentageOfBaseAmount) {
        this.name = name;
        this.amount = amount;
        this.currencySymbol = currencySymbol;
        this.percentageOfBaseAmount = percentageOfBaseAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getPercentageOfBaseAmount() {
        return percentageOfBaseAmount;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, currencySymbol);
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
                && this.currencySymbol.equals(o.currencySymbol);
    }
}
