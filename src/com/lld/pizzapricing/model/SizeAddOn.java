package com.lld.pizzapricing.model;

import com.lld.pizzapricing.constant.PizzaSize;

public class SizeAddOn extends PizzaDecorator {
    private PizzaSize pizzaSize;
    private PricingFactory pricingFactory;
    public SizeAddOn(Pizza pizza, PizzaSize size, PricingFactory pricingFactory) {
        super(pizza);
        this.pizzaSize = size;
        this.pricingFactory = pricingFactory;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " ==> " + " size " + this.pizzaSize.name() + " added.";
    }

    @Override
    public Money getCost() {
        Money existingCost = this.pizza.getCost();
        Money extraPrice = this.pricingFactory.getSizePrice(this.pizzaSize);
        return new MoneyImpl(existingCost.getCurrency(),
                existingCost.amount() + extraPrice.amount()); // Assuming the same currency
    }

    @Override
    public String toString() {
        return "SizeAddOn{" +
                "pizzaSize=" + pizzaSize +
                " size cost=" + this.pricingFactory.getSizePrice(this.pizzaSize) +
                " total cost=" + this.getCost() +
                '}';
    }
}
