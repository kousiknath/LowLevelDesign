package com.lld.amazon.util;

import com.lld.amazon.constant.CurrencySymbol;
import com.lld.amazon.constant.CustomerPreferenceKeys;
import com.lld.amazon.model.Customer;
import com.lld.amazon.model.KV;

import java.util.List;

public class Util {
    public final static CurrencySymbol getCurrencySymbol(Customer customer, List<KV> customerPrefs) {
        for (KV pref: customerPrefs) {
            if (pref.getKey().equals(CustomerPreferenceKeys.CURRENCY)) {
                return (CurrencySymbol) pref.getVal();
            }
        }

        return customer.getProfile().getDefaultCurrency();
    }
}
