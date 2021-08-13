/**
 * @(#)RpcServerConfiguration.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.configuration;

import com.coder4.homs.demo.server.HomsRpcServer;
import io.grpc.BindableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * @author coder4
 */
@Configuration
public class RpcServerConfiguration {

    private Logger LOG = LoggerFactory.getLogger(RpcServerConfiguration.class);

    @Autowired
    private BindableService bindableService;

    @Autowired
    private HomsRpcServer server;

    @Bean
    public HomsRpcServer createRpcServer() {
        return new HomsRpcServer(bindableService, 5000);
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        server.start();
    }

    @PreDestroy
    public void preDestory() {
        try {
            server.stop();
        } catch (InterruptedException e) {
            LOG.info("stop gRPC server exception", e);
        } finally {
            LOG.info("stop gRPC server done");
        }
    }


}