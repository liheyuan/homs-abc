/**
 * @(#)BaseConsumer.java, 10月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.mq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;

/**
 * @author coder4
 */
public abstract class BaseConsumer<T> implements DisposableBean {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private static final int DEFAULT_BATCH_SIZE = 1;

    private static final int MAX_RETRY = 1024;

    abstract String getNamesrvAddr();

    abstract String getConsumerGroup();

    abstract String getTopic();

    abstract String getTag();

    abstract Class<T> getClassT();

    abstract boolean process(T msg);

    private ObjectMapper objectMapper = new ObjectMapper();

    protected DefaultMQPushConsumer consumer;

    public BaseConsumer() {
        consumer = new
                DefaultMQPushConsumer(getConsumerGroup());
    }

    @PostConstruct
    public void postConstruct() {
        consumer.setNamesrvAddr(getNamesrvAddr());
        try {
            consumer.subscribe(getTopic(), getTag());
        } catch (MQClientException e) {
            LOG.error("consumer subscribe exception", e);
            throw new RuntimeException(e);
        }
        consumer.setConsumeMessageBatchMaxSize(DEFAULT_BATCH_SIZE);

        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            if (CollectionUtils.isEmpty(msgs)) {
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            if (msgs.size() != DEFAULT_BATCH_SIZE) {
                LOG.error("MessageListenerConcurrently callback msgs.size() != 1");
            }

            MessageExt msg = msgs.get(0);
            if (msg.getReconsumeTimes() >= MAX_RETRY) {
                LOG.error("reconsume exceed max retry times");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

            try {
                if (process(objectMapper.readValue(new String(msg.getBody()), getClassT()))) {
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } else {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            } catch (Exception e) {
                LOG.error("process exception", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            LOG.error("consumer start exception", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() throws Exception {
        consumer.shutdown();
    }
}