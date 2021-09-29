/**
 * @(#)BaseGrpcClient.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author coder4
 */
public abstract class AbstractGrpcClientManager<T extends MyGrpcClient> {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    protected volatile CopyOnWriteArrayList<T> clientPools = new CopyOnWriteArrayList<>();

    protected Class<T> kind;

    public AbstractGrpcClientManager(Class<T> kind) {
        this.kind = kind;
    }

    public Optional<T> getClient() {
        if (clientPools.size() == 0) {
            return Optional.empty();
        }
        int pos = ThreadLocalRandom.current().nextInt(clientPools.size());
        return Optional.ofNullable(clientPools.get(pos));
    }

    public abstract void init() throws Exception;

    public void shutdown() {
        clientPools.forEach(c -> {
            try {
                shutdown(c);
            } catch (InterruptedException e) {
                LOG.error("shutdown client exception", e);
            }
        });
    }

    protected void shutdown(MyGrpcClient client) throws InterruptedException {
        client.shutdownNow();
    }

    protected ManagedChannel buildManagedChannel(String ip, int port) throws Exception {
        return ManagedChannelBuilder
                .forTarget(ip + ":" + port)
                .usePlaintext()
                .build();
    }

}