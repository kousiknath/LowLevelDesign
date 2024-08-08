package com.lld.amazon.model;

import com.lld.amazon.constant.CurrencySymbol;

import java.util.List;

public class CustomerProfile {
    private List<Address> deliveryAddresses;
    private Address defaultDeliveryAddress;
    private CurrencySymbol defaultCurrency;

    public CustomerProfile(CurrencySymbol currencySymbol) {
        this.defaultCurrency = currencySymbol;
    }

    public List<Address> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }

    public CurrencySymbol getDefaultCurrency() {
        return defaultCurrency;
    }
}
