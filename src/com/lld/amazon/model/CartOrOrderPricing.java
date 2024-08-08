package com.lld.amazon.model;

import com.lld.amazon.constant.CurrencySymbol;
import com.lld.amazon.constant.PricingGroupType;
import com.lld.amazon.exception.PricingException;

import java.util.ArrayList;
import java.util.List;

public class CartOrOrderPricing {
    private Money totalAmount;
    // `CartOrOrderPricing` will only contain summation of all item
    // amount, delivery charges, surcharges if any
    // No item level cost break up is available in cart pricing
    private List<PricingGroup> pricingGroups;

    public CartOrOrderPricing() {
        this.pricingGroups = new ArrayList<>();
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    // Upsert more pricing groups if required at the CART level.
    // The PricingGroupType for all these items should be CART_LEVEL
    // or above
    public void upsertPricingGroup(PricingGroup group, CurrencySymbol currencySymbol) throws PricingException {
        if (group.getType() != PricingGroupType.CART_LEVEL) {
            throw new PricingException("Only Cart level pricing groups can be applied!");
        }

        int index = this.pricingGroups.indexOf(group);
        if (index < 0) {
            this.pricingGroups.add(group);
        } else {
            PricingGroup existingGroup = this.pricingGroups.get(index);

            for (PricingLineItem newLineItem: group.getPricingLineItems()) {
                existingGroup.addPricingLineItem(newLineItem, null);
            }
        }

        this.totalAmount = calculateTotalAmount(currencySymbol);
    }

    private Money calculateTotalAmount(CurrencySymbol currencySymbol) {
        Double amt = 0D;
        for (PricingGroup group: pricingGroups) {
            amt += group.getTotalAmount();
        }

        return new Money(amt, currencySymbol);
    }

    public void applyExtraCartOrOrderPricing(CartOrOrderPricing other) throws PricingException {
        if (this.totalAmount.getCurrencySymbol() != other.totalAmount.getCurrencySymbol()) {
            throw new PricingException("Can not append another CartOrOrderPricing to the current one as " +
                    "there is a currency symbol mismatch");
        }

        for (PricingGroup pricingGroup: other.pricingGroups) {
            upsertPricingGroup(pricingGroup, other.getTotalAmount().getCurrencySymbol());
        }
    }
}
