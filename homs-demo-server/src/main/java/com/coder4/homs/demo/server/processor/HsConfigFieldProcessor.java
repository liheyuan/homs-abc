/**
 * @(#)HsConfigFieldProcessor.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.processor;

import com.alibaba.nacos.common.utils.StringUtils;
import com.coder4.homs.demo.server.HsReflectionUtils;
import com.coder4.homs.demo.server.annotation.HSConfig;
import com.coder4.homs.demo.server.service.spi.NacosConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.data.util.ReflectionUtils.AnnotationFieldFilter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldFilter;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @author coder4
 */
public class HsConfigFieldProcessor implements BeanPostProcessor, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(HsConfigFieldProcessor.class);

    private static final FieldFilter HS_CONFIG_FIELD_FILTER = new AnnotationFieldFilter(HSConfig.class);

    private NacosConfigService nacosConfigService;

    private String serviceName;

    public HsConfigFieldProcessor(NacosConfigService service, String serviceName) {
        this.nacosConfigService = service;
        this.serviceName = serviceName;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class targetClass = AopUtils.getTargetClass(bean);
        ReflectionUtils.doWithFields(
                targetClass, field -> processField(bean, field), HS_CONFIG_FIELD_FILTER);
        return bean;
    }

    private void processField(Object bean, Field field) {
        HSConfig valueAnnotation = field.getDeclaredAnnotation(HSConfig.class);
        String key = StringUtils.defaultIfEmpty(valueAnnotation.name(), field.getName());
        Optional<String> valueOp = nacosConfigService.getConfig(serviceName, key);
        try {
            if (!valueOp.isPresent()) {
                LOG.error("nacos config for serviceName = {} key = {} is empty", serviceName, key);
            }
            HsReflectionUtils.setField(bean, field, valueOp.get());

            // Future Change
            nacosConfigService.onChange(serviceName, key, valueOp2 -> {
                try {
                    HsReflectionUtils.setField(bean, field, valueOp2.get());
                } catch (IllegalAccessException e) {
                    LOG.error("nacos config for serviceName = {} key = {} exception", e);
                }
            });
        } catch (IllegalAccessException e) {
            LOG.error("setField for " + field.getName() + " exception", e);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}