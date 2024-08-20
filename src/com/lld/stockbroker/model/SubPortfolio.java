package com.lld.stockbroker.model;

import java.util.ArrayList;
import java.util.List;

public class SubPortfolio {
    private List<Holding> holdings;
    private List<Holding> positions;

    public SubPortfolio() {
        this.holdings = new ArrayList<>();
        this.positions = new ArrayList<>();
    }
}
