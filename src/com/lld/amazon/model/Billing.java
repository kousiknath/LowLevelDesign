package com.lld.amazon.model;

import java.util.List;

/*
    High-level Billing which can be constructed on the fly for an order
 */
public class Billing {
    private Order order;
    // aggregated or grossly computed billing line items across
    // all the items in an order
    // It should not contain any internal line items like commission
    // or logistics charges etc
    private List<PricingLineItem> aggregatedBillingLineItems;
}
