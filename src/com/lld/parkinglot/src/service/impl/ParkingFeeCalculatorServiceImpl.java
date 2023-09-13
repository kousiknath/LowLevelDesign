package com.lld.parkinglot.src.service.impl;

import com.lld.parkinglot.src.model.Fee;
import com.lld.parkinglot.src.model.Ticket;
import com.lld.parkinglot.src.service.ParkingFeeCalculatorService;

public class ParkingFeeCalculatorServiceImpl implements ParkingFeeCalculatorService {
    @Override
    public Fee calculate(Ticket ticket) {
        long duration = System.currentTimeMillis() - ticket.getCheckInTimeMillis();
        int slotsCount = ticket.getSlots().size();

        double durationFee = Math.max(duration * 2.5, 30);
        double slotUsageFee = slotsCount * 5;

        return new Fee(durationFee + slotUsageFee, duration, "INR");
    }
}
