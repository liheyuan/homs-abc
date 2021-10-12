/**
 * @(#)DemoConsumer.java, 10月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author coder4
 */
@Service
public class DemoConsumer extends BaseConsumer<DemoMessage> {

    @Value("${rocketmq.namesrv}")
    private String namesrv;

    @Override
    String getNamesrvAddr() {
        return namesrv;
    }

    @Override
    String getConsumerGroup() {
        return "demo-consumer";
    }

    @Override
    String getTopic() {
        return "demo";
    }

    @Override
    String getTag() {
        return "*";
    }

    @Override
    Class<DemoMessage> getClassT() {
        return DemoMessage.class;
    }

    @Override
    boolean process(DemoMessage msg) {
        LOG.info("process msg = {}", msg);
        return true;
    }
}