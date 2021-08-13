/**
 * @(#)HomsDemoServer.java, 8月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author coder4
 */
public class HomsRpcServer {

    private Logger LOG = LoggerFactory.getLogger(HomsRpcServer.class);

    private Server server;

    private BindableService service;

    private int port;

    public HomsRpcServer(BindableService service, int port) {
        this.service = service;
        this.port = port;
    }

    public void start() throws IOException {
        /* The port on which the server should run */
        server = ServerBuilder.forPort(port)
                .addService(service)
                .build()
                .start();
        LOG.info("start gRPC server listening on " + port);
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(15, TimeUnit.SECONDS);
        }
    }
}