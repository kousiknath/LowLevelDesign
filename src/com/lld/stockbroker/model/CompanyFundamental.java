package com.lld.stockbroker.model;

public class CompanyFundamental {
    private Double PERatio;
    private Double PBRatio;
    private Double dividendYield;
    private Double ROE;
    private Double ROCE;
    private Double ROA;

    public CompanyFundamental() {

    }

    public void setPERatio(Double PERatio) {
        this.PERatio = PERatio;
    }

    public void setPBRatio(Double PBRatio) {
        this.PBRatio = PBRatio;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public void setROE(Double ROE) {
        this.ROE = ROE;
    }

    public void setROCE(Double ROCE) {
        this.ROCE = ROCE;
    }

    public void setROA(Double ROA) {
        this.ROA = ROA;
    }
}
