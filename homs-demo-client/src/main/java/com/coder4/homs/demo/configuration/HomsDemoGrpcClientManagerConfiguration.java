/**
 * @(#)GrpcClientConfiguration.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.configuration;

import com.coder4.homs.demo.client.AbstractGrpcClientManager;
import com.coder4.homs.demo.client.HomsDemoGrpcClient;
import com.coder4.homs.demo.client.NacosGrpcClientManager;
import com.coder4.homs.demo.constant.HomsDemoConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author coder4
 */
@Configuration
public class HomsDemoGrpcClientManagerConfiguration {

    @Bean(name = "homsDemoGrpcClientManager")
    @ConditionalOnMissingBean(name = "homsDemoGrpcClientManager")
    @ConditionalOnProperty(name = {"nacos.server"})
    public AbstractGrpcClientManager<HomsDemoGrpcClient> nacosManager(
            @Value("${nacos.server}") String nacosServer) throws Exception {
        NacosGrpcClientManager<HomsDemoGrpcClient> manager =
                new NacosGrpcClientManager<>(HomsDemoGrpcClient.class,
                        nacosServer, HomsDemoConstant.SERVICE_NAME);
        manager.init();
        return manager;
    }
}