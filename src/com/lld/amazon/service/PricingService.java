package com.lld.amazon.service;

import com.lld.amazon.constant.PricingGroupType;
import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.Money;
import com.lld.amazon.model.PricingGroup;

public interface PricingService {
    PricingGroup registerPricingGroup(String name, PricingGroupType type, boolean isBase) throws PricingException;
    PricingGroup registerPricingLineItemWithAmount(PricingGroup pricingGroup, String lineItemName,
                                                      Money money, PricingGroup baseGroup) throws PricingException;
    PricingGroup registerPricingLineItemWithPercentage(PricingGroup pricingGroup, String lineItemName,
                                                          Double percentageOfBaseAmount, Money money,
                                                          PricingGroup baseGroup) throws PricingException;
}
