package com.lld.pizzapricing.model;

import com.lld.pizzapricing.constant.BaseType;
import com.lld.pizzapricing.constant.PizzaSize;
import com.lld.pizzapricing.constant.PizzaTopping;

import java.util.HashMap;
import java.util.Map;

public class PricingFactoryImpl implements PricingFactory {
    private Map<BaseType, Double> basePrices;
    private Map<PizzaSize, Double> sizePrices;
    private Map<PizzaTopping, Double> toppingPrices;

    public PricingFactoryImpl() {
        this.basePrices = new HashMap<>();
        this.sizePrices = new HashMap<>();
        this.toppingPrices = new HashMap<>();

        putBasePrices();
        putSizePrices();
        putToppingPrices();
    }

    private void putBasePrices() {
        this.basePrices.put(BaseType.THIN_CRUST, 40D);
        this.basePrices.put(BaseType.THICK_CRUST, 50D);
        this.basePrices.put(BaseType.CHEESE_STUFFED, 100D);
        this.basePrices.put(BaseType.HOME_MADE, 110D);
    }

    private void putSizePrices() {
        this.sizePrices.put(PizzaSize.REGULAR, 100D);
        this.sizePrices.put(PizzaSize.MEDIUM, 150D);
        this.sizePrices.put(PizzaSize.LARGE, 200D);
    }

    private void putToppingPrices() {
        this.toppingPrices.put(PizzaTopping.ONIONS, 50D);
        this.toppingPrices.put(PizzaTopping.MUSHROOM, 70D);
        this.toppingPrices.put(PizzaTopping.PEPPERONI, 90D);
        this.toppingPrices.put(PizzaTopping.CHEESE_BURST, 100D);
    }

    @Override
    public Money getBasePrice(BaseType baseType) {
        Double price = this.basePrices.getOrDefault(baseType, 0D);
        return new MoneyImpl("INR", price);
    }

    @Override
    public Money getSizePrice(PizzaSize pizzaSize) {
        Double price = this.sizePrices.getOrDefault(pizzaSize, 0D);
        return new MoneyImpl("INR", price);
    }

    @Override
    public Money getToppingPrice(PizzaTopping pizzaTopping) {
        Double price = this.toppingPrices.getOrDefault(pizzaTopping, 0D);
        return new MoneyImpl("INR", price);
    }
}
