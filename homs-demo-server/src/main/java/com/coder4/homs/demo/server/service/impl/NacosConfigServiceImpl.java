/**
 * @(#)NacosConfigServiceImpl.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.impl;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.coder4.homs.demo.server.service.spi.NacosConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * @author coder4
 */
@Service
public class NacosConfigServiceImpl implements NacosConfigService{

    private static final Logger LOG = LoggerFactory.getLogger(NacosConfigServiceImpl.class);

    @Value("${nacos.server}")
    private String nacosServer;

    private ConfigService configService;

    @PostConstruct
    public void postConstruct() throws NacosException {
        configService = NacosFactory
                .createConfigService(nacosServer);
    }

    @Override
    public Optional<String> getConfig(String serviceName, String key) {
        try {
            return Optional.ofNullable(configService.getConfig(key, serviceName, 5000));
        } catch (NacosException e) {
            LOG.error("nacos get config exception for " + serviceName + " " + key, e);
            return Optional.empty();
        }
    }

    @Override
    public void onChange(String serviceName, String key, Consumer<Optional<String>> consumer) {
        try {
            configService.addListener(key, serviceName, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    consumer.accept(Optional.ofNullable(configInfo));
                }
            });
        } catch (NacosException e) {
            LOG.error("nacos add listener exception for " + serviceName + " " + key, e);
            throw new RuntimeException(e);
        }
    }
}