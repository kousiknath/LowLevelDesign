package com.lld.amazon.model;

import com.lld.amazon.constant.CurrencySymbol;

public class Money {
    private Double amount;
    private CurrencySymbol currencySymbol;

    public Money(Double amount, CurrencySymbol currencySymbol) {
        this.amount = amount;
        this.currencySymbol = currencySymbol;
    }

    // Use this constructor only when you need the symbol
    public Money(CurrencySymbol currencySymbol) {
        this.amount = null;
        this.currencySymbol = currencySymbol;
    }

    public Double getAmount() {
        return amount;
    }

    public CurrencySymbol getCurrencySymbol() {
        return currencySymbol;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currencySymbol=" + currencySymbol +
                '}';
    }
}
