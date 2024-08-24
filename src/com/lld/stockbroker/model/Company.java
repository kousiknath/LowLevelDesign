package com.lld.stockbroker.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(getSector(), company.getSector()) && Objects.equals(getCompanyTicker(), company.getCompanyTicker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSector(), getCompanyTicker());
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
