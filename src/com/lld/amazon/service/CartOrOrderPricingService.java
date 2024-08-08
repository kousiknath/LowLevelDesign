package com.lld.amazon.service;

import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.Cart;
import com.lld.amazon.model.CartOrOrderPricing;
import com.lld.amazon.model.KV;

import java.util.List;

public interface CartOrOrderPricingService {
    CartOrOrderPricing calculateCartPrice(Cart cart) throws PricingException;
    CartOrOrderPricing applyMiscellaneousCartPrice(Cart cart, List<KV> miscData) throws PricingException;
}
