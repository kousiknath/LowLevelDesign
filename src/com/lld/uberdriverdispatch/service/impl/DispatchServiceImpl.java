package com.lld.uberdriverdispatch.service.impl;

import com.lld.uberdriverdispatch.exception.ServiceException;
import com.lld.uberdriverdispatch.model.RideRequest;
import com.lld.uberdriverdispatch.service.DispatchService;

import java.util.concurrent.BlockingQueue;

public class DispatchServiceImpl implements DispatchService {
    private BlockingQueue<RideRequest> requestQueue;

    public DispatchServiceImpl(BlockingQueue<RideRequest> queue) {
        this.requestQueue = queue;
    }

    @Override
    public void requestRide(RideRequest request) throws ServiceException {
        if (request == null) {
            throw new ServiceException("Invalid request");
        }

        this.requestQueue.add(request);
    }

    @Override
    public void cancelRequest(RideRequest request) {
        // TODO: Yet to implement
    }
}
