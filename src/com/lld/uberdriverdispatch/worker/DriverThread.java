package com.lld.uberdriverdispatch.worker;

import com.lld.uberdriverdispatch.model.Driver;

import java.util.Random;
import java.util.concurrent.Callable;

class DriverThread implements Callable<DriverAcceptanceResult> {

    private Driver driver;
    private DriverThreadSharedState state;

    public DriverThread(Driver driver, DriverThreadSharedState st) {
        this.driver = driver;
        this.state = st;
    }

    @Override
    public DriverAcceptanceResult call() throws Exception {
        long toss = Math.round(Math.random());
        int waitTime = new Random().nextInt(2000);

        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        if (toss == 1) {
            if (state.setState()) {
                driver.assign();
                return new DriverAcceptanceResult(true, state.getTime(), driver);
            }
        }

        return new DriverAcceptanceResult(false, -1, null);
    }
}
