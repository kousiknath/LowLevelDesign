package com.lld.uberdriverdispatch.worker;

import com.lld.uberdriverdispatch.model.Driver;
import com.lld.uberdriverdispatch.model.RideRequest;
import com.lld.uberdriverdispatch.service.DriverService;
import com.lld.uberdriverdispatch.service.impl.DriverServiceImpl;

import java.util.List;
import java.util.concurrent.*;

public class DispatchThread implements Runnable {
    private BlockingQueue<RideRequest> rideRequests;
    private ExecutorService executorService;
    private DriverService driverService;

    public DispatchThread(BlockingQueue<RideRequest> rideRequests) {
        this.rideRequests = rideRequests;
        this.executorService = Executors.newFixedThreadPool(10);
        this.driverService = new DriverServiceImpl();
    }

    @Override
    public void run() {
        while (true) {
            try {
                RideRequest request = this.rideRequests.poll(200, TimeUnit.MILLISECONDS);

                if (request != null) {
                    System.out.println("Processing request: " + request.getId());
                    dispatch(request);
                }

                Thread.sleep(500);

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void dispatch(RideRequest request) throws InterruptedException, ExecutionException {
        List<Driver> drivers = this.driverService.fetchNearbyDrivers(request.getLocation());
        DriverThreadSharedState sharedState = new DriverThreadSharedState();
        List<DriverThread> driverThreads = drivers.stream()
                .map(d -> new DriverThread(d, sharedState)).toList();
        List<Future<DriverAcceptanceResult>> results = this.executorService.invokeAll(driverThreads);

        try {
            Thread.sleep(4000); // Not efficient but wait for some time for all the threads to finish
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Driver acceptedBy = null;

        for (Future<DriverAcceptanceResult> result: results) {
            if (result.isDone() && result.get().isAccepted()) {
                DriverAcceptanceResult acceptance = result.get();
                acceptedBy = acceptance.getAcceptedBy();

                System.out.println("Request = " + request.getId()
                        + " is accepted by = " + acceptedBy.getName()
                        + ", driver status = " + acceptedBy.getStatus());
                break;
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

//        Now release the driver meaning completing the trip
//        if (acceptedBy != null) {
//            acceptedBy.release();
//            System.out.println("Request = " + request.getId() + ", released driver, status: " + acceptedBy.getStatus());
//        }

//        this.executorService.awaitTermination(2, TimeUnit.MINUTES);
    }
}
