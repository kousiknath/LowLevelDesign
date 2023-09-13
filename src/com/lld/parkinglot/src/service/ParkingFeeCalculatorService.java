package com.lld.parkinglot.src.service;

import com.lld.parkinglot.src.model.Fee;
import com.lld.parkinglot.src.model.Ticket;

public interface ParkingFeeCalculatorService {
    Fee calculate(Ticket ticket);
}
