package com.lld.amazon.service;

import com.lld.amazon.model.Address;
import com.lld.amazon.model.DeliveryETA;

public interface ETAService {
    DeliveryETA calculateETA(Address from, Address to);
}
