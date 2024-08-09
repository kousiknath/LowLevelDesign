package com.lld.amazon.model;

import com.lld.amazon.constant.CurrencySymbol;
import com.lld.amazon.constant.PricingGroupType;
import com.lld.amazon.exception.PricingException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PricingGroup {
    private String name;
    private PricingGroupType type;
    private boolean isBaseGroup;
    // Summation of the amount of all PricingLineItem
    private Double totalAmount;
    // There are a certain pricing groups that apply on the
    // base amount for a given item, ex: 30% discount
    // on base.
    // So, we need to know what's the designated base group
    // and there will be only one base group per item.
    // No cart level pricing applies on base amount of any
    // product variety
    private final Set<PricingLineItem> pricingLineItems;

    public PricingGroup(String name, PricingGroupType type, boolean isBaseGroup) {
        this.name = name;
        this.type = type;
        this.isBaseGroup = isBaseGroup;

        this.pricingLineItems = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public PricingGroupType getType() {
        return type;
    }

    public boolean isBaseGroup() {
        return isBaseGroup;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public Set<PricingLineItem> getPricingLineItems() {
        return Collections.unmodifiableSet(pricingLineItems);
    }

    public void addPricingLineItem(PricingLineItem lineItem, PricingGroup baseGroup) throws PricingException {
        this.pricingLineItems.add(lineItem);
        this.totalAmount = calculateTotal(baseGroup);
    }

    private Double calculateTotal(PricingGroup baseGroup) throws PricingException {
        Double amount = 0D;
        CurrencySymbol symbol = null;

        for (PricingLineItem lineItem: pricingLineItems) {
            if (symbol == null) {
                symbol = lineItem.getAmount().getCurrencySymbol();
            } else if (!symbol.equals(lineItem.getAmount().getCurrencySymbol())) {
                throw new PricingException("Inconsistent symbols in pricing line items");
            }

            // baseGroup and percentage of base amount will be null for base amount
            if (baseGroup == null && lineItem.getPercentageOfBaseAmount() == null) {
                if (lineItem.getAmount() == null) {
                    throw new PricingException("Amount must be non-null in base amount");
                }
            }

            if (lineItem.getAmount() == null) {
                if (baseGroup == null && lineItem.getPercentageOfBaseAmount() == null) {
                    throw new PricingException("Percentage of base amount and base group can't " +
                            "be null when amount is not available");
                }
            }

            if (lineItem.getAmount() != null && lineItem.getAmount().getAmount() != null) {
                amount += lineItem.getAmount().getAmount();
            } else if (lineItem.getPercentageOfBaseAmount() != null) {
                amount = amount + baseGroup.totalAmount * lineItem.getPercentageOfBaseAmount() * 0.01;
            }
        }

        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, isBaseGroup);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        PricingGroup other = (PricingGroup) obj;
        return this.name.equals(other.name)
                && this.type == other.type
                && this.isBaseGroup == other.isBaseGroup;
    }
}
