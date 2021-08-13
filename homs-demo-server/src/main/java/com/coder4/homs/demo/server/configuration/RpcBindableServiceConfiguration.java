/**
 * @(#)RpcBindableServiceConfiguration.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.configuration;

import com.coder4.homs.demo.server.grpc.HomsDemoGrpcImpl;
import io.grpc.BindableService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author coder4
 */
@Configuration
public class RpcBindableServiceConfiguration {

    @Bean
    public BindableService createBindableService() {
        return new HomsDemoGrpcImpl();
    }

}