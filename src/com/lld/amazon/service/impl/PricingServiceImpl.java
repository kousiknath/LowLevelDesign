package com.lld.amazon.service.impl;

import com.lld.amazon.constant.PricingGroupType;
import com.lld.amazon.exception.PricingException;
import com.lld.amazon.model.*;
import com.lld.amazon.service.PricingService;

import java.util.List;

public class PricingServiceImpl implements PricingService {

    public PricingServiceImpl() {}

    @Override
    public PricingGroup registerPricingGroup(String name, PricingGroupType type, boolean isBase) throws PricingException {
        if (isBase) {
            if (type != PricingGroupType.ITEM_LEVEL) {
                throw new PricingException("Invalid pricing group");
            }
        }

        return new PricingGroup(name, type, isBase);
    }

    @Override
    public PricingGroup registerPricingLineItemWithAmount(PricingGroup pricingGroup, String lineItemName, Money money,
                                                          PricingGroup baseGroup) throws PricingException {
        if (baseGroup != null && !baseGroup.isBaseGroup()) {
            throw new PricingException("Invalid base pricing group applied");
        }

        if (pricingGroup == null) {
            throw new PricingException("Invalid pricing group name");
        }

        if (lineItemName == null) {
            throw new PricingException("Invalid pricing line item name");
        }

        if (money.getCurrencySymbol() == null) {
            throw new PricingException("Invalid pricing line symbol - can not be null");
        }

        if (money.getAmount() == null) {
            throw new PricingException("Amount can not be null");
        }

        PricingLineItem pricingLineItem = new PricingLineItem(lineItemName, money.getAmount(),
                money.getCurrencySymbol().name(), null);
        pricingGroup.addPricingLineItem(pricingLineItem, baseGroup);

        return pricingGroup;
    }

    @Override
    public PricingGroup registerPricingLineItemWithPercentage(PricingGroup pricingGroup, String lineItemName,
                                                                 Double percentageOfBaseAmount, Money money, PricingGroup baseGroup)
            throws PricingException {
        if (baseGroup != null && !baseGroup.isBaseGroup()) {
            throw new PricingException("Invalid base pricing group applied");
        }

        if (pricingGroup == null) {
            throw new PricingException("Invalid pricing group name");
        }

        if (lineItemName == null) {
            throw new PricingException("Invalid pricing line item name");
        }

        if (money.getCurrencySymbol() == null) {
            throw new PricingException("Invalid pricing line symbol - can not be null");
        }

        if (percentageOfBaseAmount == null) {
            throw new PricingException("Percentage of base amount can not be null");
        }

        PricingLineItem pricingLineItem = new PricingLineItem(lineItemName, null, money.getCurrencySymbol().name(),
                percentageOfBaseAmount);
        pricingGroup.addPricingLineItem(pricingLineItem, baseGroup);

        return pricingGroup;
    }
}
