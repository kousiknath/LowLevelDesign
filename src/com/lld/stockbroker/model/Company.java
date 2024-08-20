package com.lld.stockbroker.model;

public class Company {
    private String name;
    private Sector sector;
    private String marketCap;
    private CompanyTicker companyTicker;
    private CompanyFundamental companyFundamental;
    private CompanyGrowth companyGrowth;

    public Company(String name, Sector sector, CompanyTicker companyTicker) {
        this.name = name;
        this.sector = sector;
        this.companyTicker = companyTicker;
    }

    public String getName() {
        return name;
    }

    public Sector getSector() {
        return sector;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public CompanyTicker getCompanyTicker() {
        return companyTicker;
    }

    public CompanyFundamental getCompanyFundamental() {
        return companyFundamental;
    }

    public CompanyGrowth getCompanyGrowth() {
        return companyGrowth;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public void setCompanyFundamental(CompanyFundamental companyFundamental) {
        this.companyFundamental = companyFundamental;
    }

    public void setCompanyGrowth(CompanyGrowth companyGrowth) {
        this.companyGrowth = companyGrowth;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", sector=" + sector +
                ", companyTicker=" + companyTicker +
                '}';
    }
}
