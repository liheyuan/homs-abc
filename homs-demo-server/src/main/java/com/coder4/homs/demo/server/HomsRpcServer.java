/**
 * @(#)HomsDemoServer.java, 8月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server;

import com.coder4.homs.demo.server.grpc.HomsDemoGrpcImpl;
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

    private void start() throws IOException {
        /* The port on which the server should run */
        server = ServerBuilder.forPort(port)
                .addService(service)
                .build()
                .start();
        LOG.info("Start gRPC server listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                LOG.info("Shutting down gRPC server since JVM is shutting down");
                try {
                    HomsRpcServer.this.stop();
                } catch (InterruptedException e) {
                    LOG.error("Shutdown gRPC server exception", e);
                }
                LOG.error("Shutdown gRPC server done");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // Test For Main
    public static void main(String[] args) {
        try {
            final HomsRpcServer server = new HomsRpcServer(new HomsDemoGrpcImpl(), 5000);
            server.start();
            server.blockUntilShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}