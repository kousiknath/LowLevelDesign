package com.lld.amazon.service.impl;

import com.lld.amazon.constant.*;
import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.*;
import com.lld.amazon.service.CartOrOrderPricingService;
import com.lld.amazon.service.PricingService;
import com.lld.amazon.util.Util;

import java.util.List;
import java.util.Set;

public class CartOrOrderPricingServiceImpl implements CartOrOrderPricingService {
    private PricingService pricingService;
    private Customer customer;
    private List<KV> customerPrefs;

    public CartOrOrderPricingServiceImpl(PricingService pricingService, Customer customer, List<KV> customerPrefs) {
        this.pricingService = pricingService;
        this.customer = customer;
        this.customerPrefs = customerPrefs;
    }

    @Override
    public CartOrOrderPricing calculateCartPrice(Cart cart) throws PricingException {
        CartOrOrderPricing cartOrOrderPricing = new CartOrOrderPricing();
        // TODO: Fetch from rules engine or from some config what all pricing groups
        // to apply to a cart
        cartOrOrderPricing.upsertPricingGroup(getSubTotalPricingGroup(cart.getCartOrOrderQuantities()),
                Util.getCurrencySymbol(customer, customerPrefs));
        return cartOrOrderPricing;
    }

    @Override
    public CartOrOrderPricing applyMiscellaneousCartPrice(Cart cart, List<KV> miscData) throws PricingException {
        CartOrOrderPricing cartOrOrderPricing = new CartOrOrderPricing();

        // TODO: Pass all the essential cart parameters to rule engine
        // and get a list of applicable extra charges rather than
        // hardcoding.
        if (cart.getPaymentMethod().getMethodType() == PaymentMethodType.CASH_ON_DELIVERY) {
            cartOrOrderPricing.upsertPricingGroup(getCashOnDeliveryConvenienceChargesPricingGroup(),
                    Util.getCurrencySymbol(customer, customerPrefs));
        }

        DeliveryOption deliveryOption = cart.getDeliveryOption();
        if (deliveryOption.getDeliveryType() == DeliveryType.SAME_DAY_DELIVERY) {
            cartOrOrderPricing.upsertPricingGroup(applySameDayDelivery(cart), Util.getCurrencySymbol(customer, customerPrefs));
        } else if (deliveryOption.getDeliveryType() == DeliveryType.ONE_DAY_DELIVERY) {
            cartOrOrderPricing.upsertPricingGroup(applyOneDayDelivery(cart), Util.getCurrencySymbol(customer, customerPrefs));
        } else if (deliveryOption.getDeliveryType() == DeliveryType.TWO_DAY_DELIVERY) {
            cartOrOrderPricing.upsertPricingGroup(applyTwoDayDelivery(cart), Util.getCurrencySymbol(customer, customerPrefs));
        } else if (deliveryOption.getDeliveryType() == DeliveryType.STANDARD_DELIVERY) {
            cartOrOrderPricing.upsertPricingGroup(applyStandardDelivery(cart), Util.getCurrencySymbol(customer, customerPrefs));
        }

        // If offers are applicable on any product variety, apply them here

        return cartOrOrderPricing;
    }

    private PricingGroup getSubTotalPricingGroup(Set<QuantifiedCartOrOrder> items) throws PricingException {
        Double amount = 0D;

        for (QuantifiedCartOrOrder item: items) {
            amount += item.getOrderItem().getChosenVariety().getPricing().getTotalAmount() * item.getQuantity();
        }

        PricingGroup subTotal = this.pricingService.registerPricingGroup(PricingGroups.SUBTOTAL, PricingGroupType.CART_LEVEL, false);
        // TODO: Remove currency symbol hardcoding from the following
        return this.pricingService.registerPricingLineItemWithAmount(subTotal,
                PricingLineItems.SUBTOTAL,
                new Money(amount, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }

    private PricingGroup getCashOnDeliveryConvenienceChargesPricingGroup() throws PricingException {
        PricingGroup cod = this.pricingService.registerPricingGroup(PricingGroups.CASH_ON_DELIVERY,
                PricingGroupType.CART_LEVEL, false);
        // TODO: Fetch delivery charges from some rules engine, don't hardcode
        return this.pricingService.registerPricingLineItemWithAmount(cod,
                PricingLineItems.COD_CONVENIENCE_CHARGES,
                new Money(10D, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }

    private PricingGroup applySameDayDelivery(Cart cart) throws PricingException {
        PricingGroup cod = this.pricingService.registerPricingGroup(PricingGroups.DELIVERY_CHARGES,
                PricingGroupType.CART_LEVEL, false);
        // TODO: Fetch delivery charges from some rules engine, don't hardcode
        return this.pricingService.registerPricingLineItemWithAmount(cod,
                PricingLineItems.SAME_DAY_DELIVERY_CHARGE,
                new Money(179D, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }

    private PricingGroup applyOneDayDelivery(Cart cart) throws PricingException {
        PricingGroup cod = this.pricingService.registerPricingGroup(PricingGroups.DELIVERY_CHARGES,
                PricingGroupType.CART_LEVEL, false);
        // TODO: Fetch delivery charges from some rules engine, don't hardcode
        return this.pricingService.registerPricingLineItemWithAmount(cod,
                PricingLineItems.ONE_DAY_DELIVERY_CHARGE,
                new Money(111D, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }

    private PricingGroup applyTwoDayDelivery(Cart cart) throws PricingException {
        PricingGroup cod = this.pricingService.registerPricingGroup(PricingGroups.DELIVERY_CHARGES,
                PricingGroupType.CART_LEVEL, false);
        // TODO: Fetch delivery charges from some rules engine, don't hardcode
        return this.pricingService.registerPricingLineItemWithAmount(cod,
                PricingLineItems.TWO_DAY_DELIVERY_CHARGE,
                new Money(79D, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }

    private PricingGroup applyStandardDelivery(Cart cart) throws PricingException {
        PricingGroup cod = this.pricingService.registerPricingGroup(PricingGroups.DELIVERY_CHARGES,
                PricingGroupType.CART_LEVEL, false);
        // TODO: Fetch delivery charges from some rules engine, don't hardcode
        return this.pricingService.registerPricingLineItemWithAmount(cod,
                PricingLineItems.STANDARD_DELIVERY_CHARGE,
                new Money(40D, Util.getCurrencySymbol(customer, customerPrefs)), null);
    }
}
