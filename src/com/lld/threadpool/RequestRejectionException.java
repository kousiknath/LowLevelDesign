package com.lld.threadpool;

public class RequestRejectionException extends Exception {

    public RequestRejectionException(String msg) {
        super(msg);
    }
}
