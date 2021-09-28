/**
 * @(#)NacosServiceImpl.java, 9月 28, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.impl;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.coder4.homs.demo.server.service.spi.NacosService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author coder4
 */
@Service
public class NacosServiceImpl implements NacosService {

    @Value("${nacos.server}")
    private String nacosServer;

    private NamingService namingService;

    @PostConstruct
    public void postConstruct() throws NacosException {
        namingService = NamingFactory
                .createNamingService(nacosServer);
    }

    @Override
    public void registerRPC(String serviceName) throws NacosException {
        namingService.registerInstance(serviceName, getIP(), 5000);
    }

    @Override
    public void deregisterRPC(String serviceName) throws NacosException {
        namingService.deregisterInstance(serviceName, getIP(), 5000);
    }

    private String getIP() {
        return System.getProperty("POD_IP", "127.0.0.1");
    }
}