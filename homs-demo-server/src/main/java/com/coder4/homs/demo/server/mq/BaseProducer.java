/**
 * @(#)BaseProducer.java, 10月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

/**
 * @author coder4
 */
public abstract class BaseProducer<T> implements DisposableBean {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    abstract String getNamesrvAddr();

    abstract String getProducerGroup();

    abstract String getTopic();

    abstract String getTag();

    protected DefaultMQProducer producer;

    private ObjectMapper objectMapper = new ObjectMapper();

    public BaseProducer() {
        producer = new
                DefaultMQProducer(getProducerGroup());
    }

    @PostConstruct
    public void postConstruct() {
        producer.setNamesrvAddr(getNamesrvAddr());
        try {
            producer.start();
        } catch (MQClientException e) {
            LOG.error("producer start exception", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() throws Exception {
        producer.shutdown();
    }

    protected Message buildMessage(String payload) {
        return new Message(getTopic(),
                getTag(),
                payload.getBytes(StandardCharsets.UTF_8)
        );
    }

    public void publish(T payload) {
        try {
            String val = objectMapper.writeValueAsString(payload);
            producer.send(buildMessage(val));
            LOG.info("publish success, topic = {}, tag = {}, msg = {}", getTopic(), getTag(), val);
        } catch (Exception e) {
            LOG.error("publish exception", e);
        }
    }

    public void publishAsync(T payload) {
        try {
            String val = objectMapper.writeValueAsString(payload);
            producer.send(buildMessage(val), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    LOG.info("publishAsync success, topic = {}, tag = {}, msg = {}", getTopic(), getTag(), val);
                }

                @Override
                public void onException(Throwable e) {
                    LOG.error("publish async exception", e);
                }
            });
        } catch (Exception e) {
            LOG.error("publishAsync exception", e);
        }
    }

}