package com.lld.amazon.constant;

public enum CurrencySymbol {
    INR("INR"),
    USD("USD");
    private final String val;
    CurrencySymbol(String symbol) {
        this.val = symbol;
    }
}
