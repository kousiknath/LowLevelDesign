package com.lld.stockbroker.service;

import com.lld.stockbroker.exception.ExchangeException;
import com.lld.stockbroker.model.Company;
import com.lld.stockbroker.model.CompanyTicker;

import java.util.Map;

public interface ExchangeService {
    void registerCompany(Company company) throws ExchangeException;
    CompanyTicker getTicker(String stockSymbol);
    Map<String, CompanyTicker> getAllTickers();
}
