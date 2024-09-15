package com.lld.pizzapricing.model;

public class BasePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Base Pizza! The minimum Pizza!";
    }

    @Override
    public Money getCost() {
        return new MoneyImpl("INR", 100D);
    }

    @Override
    public String toString() {
        return "Base pizza with cost = " + this.getCost();
    }
}
