/**
 * @(#)NacosConfigService.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.spi;

import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author coder4
 */
public interface NacosConfigService {

    Optional<String> getConfig(String serviceName, String key);

    void onChange(String serviceName, String key, Consumer<Optional<String>> consumer);

}