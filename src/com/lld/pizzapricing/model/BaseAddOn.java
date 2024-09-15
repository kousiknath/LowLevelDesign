package com.lld.pizzapricing.model;

import com.lld.pizzapricing.constant.BaseType;

public class BaseAddOn extends PizzaDecorator {
    private BaseType baseType;
    private PricingFactory pricingFactory;
    public BaseAddOn(Pizza pizza, BaseType baseType, PricingFactory pricingFactory) {
        super(pizza);
        this.baseType = baseType;
        this.pricingFactory = pricingFactory;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " ==> " + "base type " + baseType.name() + " added";
    }

    @Override
    public Money getCost() {
        Money existingCost = this.pizza.getCost();
        Money extraPrice = this.pricingFactory.getBasePrice(this.baseType);
        return new MoneyImpl(existingCost.getCurrency(),
                existingCost.amount() + extraPrice.amount()); // Assuming the same currency
    }

    @Override
    public String toString() {
        return "BaseAddOn{" +
                "baseType=" + baseType +
                " base cost=" + this.pricingFactory.getBasePrice(this.baseType) +
                " total cost=" + this.getCost() +
                '}';
    }
}
