package com.lld.stockbroker.service.impl;

import com.lld.stockbroker.constant.Currency;
import com.lld.stockbroker.exception.ExchangeException;
import com.lld.stockbroker.model.Company;
import com.lld.stockbroker.model.CompanyTicker;
import com.lld.stockbroker.model.Money;
import com.lld.stockbroker.repository.ExchangeRepository;
import com.lld.stockbroker.service.ExchangeService;


import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ExchangeServiceImpl implements ExchangeService {
    private ExchangeRepository exchangeRepository;

    public ExchangeServiceImpl() {
        this.exchangeRepository = new ExchangeRepository();
        updateTickers();
    }

    private void updateTickers() {
        new Thread(new TickerUpdater(this.exchangeRepository)).start();
    }

    @Override
    public void registerCompany(Company company) throws ExchangeException {
        if (company == null) {
            throw new ExchangeException("Company can not be null!");
        }

        this.exchangeRepository.register(company);
    }

    @Override
    public CompanyTicker getTicker(String stockSymbol) {
        return null;
    }

    @Override
    public Map<String, CompanyTicker> getAllTickers() {
        return null;
    }
}

// TODO: Handle concurrency
class TickerUpdater implements Runnable {
    private ExchangeRepository repository;
    public TickerUpdater(ExchangeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        while (true) {
            for (String symbol : this.repository.getAllEntries().keySet()) {
                Double price = (double) ThreadLocalRandom.current().nextInt(10000, 11000 + 1);
                Double extra = new Random().nextDouble();
                Double finalPrice = price + extra;
                // Apply the pricing here to the company ticker
                try {
                    this.repository.updateTicker(symbol, new Money(finalPrice, Currency.INR));
                } catch (ExchangeException e) {
                    System.out.println(e.getMessage());
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}