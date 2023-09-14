package com.lld.uberdriverdispatch;


import com.lld.uberdriverdispatch.exception.ServiceException;
import com.lld.uberdriverdispatch.model.Location;
import com.lld.uberdriverdispatch.model.Product;
import com.lld.uberdriverdispatch.model.RideRequest;
import com.lld.uberdriverdispatch.model.User;
import com.lld.uberdriverdispatch.service.DispatchService;
import com.lld.uberdriverdispatch.service.impl.DispatchServiceImpl;
import com.lld.uberdriverdispatch.worker.DispatchThread;

import java.util.Random;
import java.util.concurrent.*;


public class DispatchApplication {
    private static void test1() throws InterruptedException, ExecutionException, ServiceException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        BlockingQueue<RideRequest> requestQueue = new LinkedBlockingQueue<>();

        DispatchService dispatchService = new DispatchServiceImpl(requestQueue);
        executorService.execute(new DispatchThread(requestQueue));


        for (int i = 0; i < 20; i++) {
            User user = new User("user-" + i, "user-" + i + "@test.com");
            Product product = new Product("product-" + i);
            Location location = new Location("address-" + i,
                    new Random().nextDouble(200.0d), new Random().nextDouble(100.0d));
            RideRequest request = new RideRequest(user, product, location);
            dispatchService.requestRide(request);

            Thread.sleep(1000);
        }

        executorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, ServiceException {
        test1();
    }
}