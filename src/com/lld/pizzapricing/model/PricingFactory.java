package com.lld.pizzapricing.model;

import com.lld.pizzapricing.constant.BaseType;
import com.lld.pizzapricing.constant.PizzaSize;
import com.lld.pizzapricing.constant.PizzaTopping;

public interface PricingFactory {
    Money getBasePrice(BaseType baseType);
    Money getSizePrice(PizzaSize pizzaSize);
    Money getToppingPrice(PizzaTopping pizzaTopping);
}
