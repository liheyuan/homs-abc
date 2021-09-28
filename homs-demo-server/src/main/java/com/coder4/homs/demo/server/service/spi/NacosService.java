/**
 * @(#)NacosService.java, 9月 28, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.service.spi;

import com.alibaba.nacos.api.exception.NacosException;

/**
 * @author coder4
 */
public interface NacosService {

    void registerRPC(String serviceName) throws NacosException;

    void deregisterRPC(String serviceName) throws NacosException;

}