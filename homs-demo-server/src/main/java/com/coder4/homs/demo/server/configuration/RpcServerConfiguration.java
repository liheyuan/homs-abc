/**
 * @(#)RpcServerConfiguration.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.configuration;

import com.alibaba.nacos.api.exception.NacosException;
import com.coder4.homs.demo.server.HomsRpcServer;
import com.coder4.homs.demo.server.service.spi.NacosService;
import io.grpc.BindableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

import static com.coder4.homs.demo.constant.HomsDemoConstant.SERVICE_NAME;

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

    @Autowired
    private NacosService nacosService;

    @Bean
    public HomsRpcServer createRpcServer() {
        return new HomsRpcServer(bindableService, 5000);
    }

    @PostConstruct
    public void postConstruct() throws IOException, NacosException {
        server.start();
        nacosService.registerRPC(SERVICE_NAME);
    }

    @PreDestroy
    public void preDestory() throws NacosException {
        try {
            server.stop();
        } catch (InterruptedException e) {
            LOG.info("stop gRPC server exception", e);
        } finally {
            nacosService.deregisterRPC(SERVICE_NAME);
            LOG.info("stop gRPC server done");
        }
    }


}