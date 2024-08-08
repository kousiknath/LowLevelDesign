package com.lld.amazon.model;

import com.lld.amazon.exception.PricingException;

import java.util.ArrayList;
import java.util.List;

public class Pricing {
    private Double totalAmount;
    private List<PricingGroup> pricingGroups;

    public Pricing() {
        this.pricingGroups = new ArrayList<>();
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void addPricingGroup(PricingGroup group) throws PricingException {
        if (group.isBaseGroup()) {
            checkIfBaseGroupAlreadyExists();
        }

        this.pricingGroups.add(group);
        this.totalAmount = calculateTotal();
    }

    private void checkIfBaseGroupAlreadyExists() throws PricingException {
        for (PricingGroup group: this.pricingGroups) {
            if (group.isBaseGroup()) {
                throw new PricingException("Duplicate base group can not be added");
            }
        }
    }

    private Double calculateTotal() {
        Double amt = 0D;
        for (PricingGroup group: pricingGroups) {
            amt += group.getTotalAmount();
        }

        return amt;
    }
}
