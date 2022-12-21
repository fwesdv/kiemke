package com.example.test.Api;

import okhttp3.Dispatcher;

public class CustomDispatcher {
    public Dispatcher dispatcher;

    public CustomDispatcher() {
        this.dispatcher = new Dispatcher();
        this.dispatcher.setMaxRequests(1);
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
