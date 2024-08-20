package com.lld.stockbroker.repository;

import com.lld.stockbroker.exception.ExchangeException;
import com.lld.stockbroker.model.Company;
import com.lld.stockbroker.model.CompanyTicker;
import com.lld.stockbroker.model.Money;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRepository {
    private Map<String, Company> companies; // Ticker to Company map

    public ExchangeRepository() {
        this.companies = new HashMap<>();
    }

    public void register(Company company) {
        this.companies.put(company.getCompanyTicker().getStockSymbol(), company);
    }

    public Map<String, Company> getAllEntries() {
        return Collections.unmodifiableMap(this.companies);
    }

    public void updateTicker(String symbol, Money amount) throws ExchangeException {
        Company existingCompany = this.companies.get(symbol);
        if (existingCompany == null) {
            throw new ExchangeException("Invalid company");
        }

        this.companies.get(symbol).getCompanyTicker().feedValue(amount);
    }
}
