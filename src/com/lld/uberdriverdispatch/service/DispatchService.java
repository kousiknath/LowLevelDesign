package com.lld.uberdriverdispatch.service;

import com.lld.uberdriverdispatch.exception.ServiceException;
import com.lld.uberdriverdispatch.model.RideRequest;

public interface DispatchService {
    void requestRide(RideRequest request) throws ServiceException;
    void cancelRequest(RideRequest request);
}
