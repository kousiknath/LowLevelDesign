package com.lld.pizzapricing.model;

import com.lld.pizzapricing.constant.PizzaTopping;

public class ToppingAddOn extends PizzaDecorator {
    private PizzaTopping topping;
    private PricingFactory pricingFactory;
    public ToppingAddOn(Pizza pizza, PizzaTopping topping, PricingFactory pricingFactory) {
        super(pizza);
        this.topping = topping;
        this.pricingFactory = pricingFactory;
    }

    @Override
    public String getDescription() {
        return this.pizza.getDescription() + " ==> " + " Topping " + this.topping.name() + " applied";
    }

    @Override
    public Money getCost() {
        Money existingCost = this.pizza.getCost();
        Money extraPrice = this.pricingFactory.getToppingPrice(this.topping);
        return new MoneyImpl(existingCost.getCurrency(),
                existingCost.amount() + extraPrice.amount()); // Assuming the same currency
    }

    @Override
    public String toString() {
        return "ToppingAddOn{" +
                "topping=" + topping +
                " topping cost=" + this.pricingFactory.getToppingPrice(this.topping) +
                " total cost=" + this.getCost() +
                '}';
    }
}
