/**
 * @(#)HsConfigProcessorConfiguration.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.configuration;

import com.coder4.homs.demo.constant.HomsDemoConstant;
import com.coder4.homs.demo.server.processor.HsConfigFieldProcessor;
import com.coder4.homs.demo.server.service.spi.NacosConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author coder4
 */
@Configuration
public class HsConfigProcessorConfiguration {

    @Bean
    @ConditionalOnMissingBean(HsConfigFieldProcessor.class)
    public HsConfigFieldProcessor fieldProcessor(@Autowired NacosConfigService configService) {
        return new HsConfigFieldProcessor(configService, HomsDemoConstant.SERVICE_NAME);
    }

}