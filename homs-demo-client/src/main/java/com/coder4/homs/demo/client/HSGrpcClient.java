/**
 * @(#)GrpcClient.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author coder4
 */
public abstract class HSGrpcClient implements AutoCloseable {

    private ManagedChannel channel;

    private String ip;

    private int port;

    public HSGrpcClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void init() {
        channel = ManagedChannelBuilder
                .forTarget(ip + ":" + port)
                .usePlaintext()
                .build();
        initSub(channel);
    }

    protected abstract void initSub(Channel channel);

    public void close() throws InterruptedException {
        channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }

}