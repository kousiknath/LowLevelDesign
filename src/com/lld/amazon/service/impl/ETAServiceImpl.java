package com.lld.amazon.service.impl;

import com.lld.amazon.model.Address;
import com.lld.amazon.model.DeliveryETA;
import com.lld.amazon.service.ETAService;

import java.time.LocalDateTime;
import java.util.Random;

public class ETAServiceImpl implements ETAService {
    @Override
    public DeliveryETA calculateETA(Address from, Address to) {
        //TODO: Calculate ETA using from and to pin code
        // For now, return a random ETA
        return new DeliveryETA(LocalDateTime.now().plusDays(new Random().nextInt(7)));
    }
}
