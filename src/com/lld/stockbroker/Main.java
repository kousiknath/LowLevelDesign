package com.lld.stockbroker;

import com.lld.stockbroker.constant.AssetType;
import com.lld.stockbroker.constant.SectorType;
import com.lld.stockbroker.exception.ExchangeException;
import com.lld.stockbroker.exception.PortfolioException;
import com.lld.stockbroker.model.*;
import com.lld.stockbroker.service.ExchangeService;
import com.lld.stockbroker.service.PortfolioService;
import com.lld.stockbroker.service.impl.ExchangeServiceImpl;
import com.lld.stockbroker.service.impl.PortfolioServiceImpl;

public class Main {

    private static void test1() throws ExchangeException, PortfolioException {
        User user = new User("test", "test@test.com");
        ExchangeService exchangeService = new ExchangeServiceImpl();

        Sector sector1 = new Sector(SectorType.FINANCIALS);
        Sector sector2 = new Sector(SectorType.IT);
        Sector sector3 = new Sector(SectorType.HEALTH_CARE);

        Exchange nseExchange = new Exchange("NSE");
        Exchange nasdaqExchange = new Exchange("NSE");

        CompanyTicker companyTicker1 = new CompanyTicker("HDFCBANK", nseExchange);
        Company company1 = new Company("HDFC Bank", sector1, companyTicker1);
        exchangeService.registerCompany(company1);

        CompanyTicker companyTicker2 = new CompanyTicker("MSFT", nasdaqExchange);
        Company company2 = new Company("Microsoft", sector2, companyTicker2);
        exchangeService.registerCompany(company2);

        CompanyTicker companyTicker3 = new CompanyTicker("APOLLO", nseExchange);
        Company company3 = new Company("Apollo", sector3, companyTicker3);
        exchangeService.registerCompany(company3);

        PortfolioService portfolioService = new PortfolioServiceImpl();
        portfolioService.addToWatchList(AssetType.EQUITY, company1, user);
        portfolioService.addToWatchList(AssetType.EQUITY, company3, user);

        try {
            Thread.sleep(2000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Watch List = " + portfolioService.getWatchList(AssetType.EQUITY, user));

        OrderData orderData = new OrderData(company1,
                new MarketOrderStrategy(
                company1.getCompanyTicker().getLatestPrice().getAmount() * 3, 3),
                AssetType.EQUITY); // TODO: Check BuyingStrategy
        Order order = portfolioService.buy(orderData, user);
        System.out.println("Order placed = " + order);
        System.out.println("Positions when the first BUY order is placed: " + portfolioService.getPositions(AssetType.EQUITY, user));

        System.out.println("Repeating the same order but with more quantity");
        orderData = new OrderData(company1,
                new MarketOrderStrategy(
                        company1.getCompanyTicker().getLatestPrice().getAmount() * 5, 5),
                AssetType.EQUITY); // TODO: Check BuyingStrategy
        order = portfolioService.buy(orderData, user);
        System.out.println("Order placed = " + order);
        System.out.println("Positions when the second BUY order is placed: " + portfolioService.getPositions(AssetType.EQUITY, user));

        System.out.println("Executing a SELL");
        orderData = new OrderData(company1,
                new MarketOrderStrategy(
                        company1.getCompanyTicker().getLatestPrice().getAmount() * 2, 2),
                AssetType.EQUITY); // TODO: Check BuyingStrategy
        order = portfolioService.sell(orderData, user);
        System.out.println("Order placed = " + order);
        System.out.println("Positions when the first SELL order is placed: " + portfolioService.getPositions(AssetType.EQUITY, user));

        System.out.println("Holdings when order not executed yet: " + portfolioService.getHoldings(AssetType.EQUITY, user));

        try {
            Thread.sleep(10_000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("Holdings when order is executed: " + portfolioService.getHoldings(AssetType.EQUITY, user));
    }

    public static void main(String[] args) throws ExchangeException, PortfolioException {
        test1();
    }
}
