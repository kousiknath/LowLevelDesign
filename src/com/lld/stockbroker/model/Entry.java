package com.lld.stockbroker.model;

import com.lld.stockbroker.exception.NotImplementedException;

public interface Entry {
    Company getCompany();
    Integer getQuantity() throws NotImplementedException;
    Double getAveragePrice() throws NotImplementedException;
    Double getTotalInvestedAmount() throws NotImplementedException;
    AmountChange getAbsoluteAmountChange();
    AmountChange getPercentageAmountChange();
}
