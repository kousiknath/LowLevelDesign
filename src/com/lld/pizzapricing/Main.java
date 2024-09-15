package com.lld.pizzapricing;

import com.lld.pizzapricing.constant.BaseType;
import com.lld.pizzapricing.constant.PizzaSize;
import com.lld.pizzapricing.constant.PizzaTopping;
import com.lld.pizzapricing.model.*;

public class Main {

    private static void test1() {
        PricingFactory pricingFactory = new PricingFactoryImpl();
        Pizza pizza = new BasePizza();
        System.out.println(pizza);
        pizza = new SizeAddOn(pizza, PizzaSize.LARGE, pricingFactory);
        System.out.println(pizza);
        pizza = new BaseAddOn(pizza, BaseType.CHEESE_STUFFED, pricingFactory);
        System.out.println(pizza);
        pizza = new ToppingAddOn(pizza, PizzaTopping.PEPPERONI, pricingFactory);
        System.out.println(pizza);

        assert pizza.getCost().amount() == 490;
    }

    public static void main(String[] args) {
        test1();
    }
}
