/**
 * @(#)HomsDemoClient.java, 8月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import com.coder4.homs.demo.HomsDemoGrpc;

import com.coder4.homs.demo.HomsDemoProto.AddRequest;
import com.coder4.homs.demo.HomsDemoProto.AddResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * @author coder4
 */
public class HomsDemoGrpcClient extends HSGrpcClient {

    private Logger LOG = LoggerFactory.getLogger(HomsDemoGrpcClient.class);


    private HomsDemoGrpc.HomsDemoFutureStub futureStub;

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    public HomsDemoGrpcClient(String ip, int port) {
        super(ip, port);
    }

    @Override
    protected void initSub(Channel channel) {
        futureStub = HomsDemoGrpc.newFutureStub(channel);
    }

    public Optional<Integer> add(int val1, int val2) {
        AddRequest request = AddRequest.newBuilder().setVal1(val1).setVal2(val2).build();
        try {

            AddResponse response = futureStub.add(request).get();
            return Optional.ofNullable(response.getVal());
        } catch (Exception e) {
            LOG.error("grpc add exception", e);
            return Optional.empty();
        }
    }

    // Test
    public static void main(String[] args) throws InterruptedException {

        Logger LOG = LoggerFactory.getLogger(HomsDemoGrpcClient.class);

        try (HomsDemoGrpcClient client = new HomsDemoGrpcClient("127.0.0.1", 5000)) {
            client.init();
            System.out.println(client.add(1, 2));
        } catch (Exception e) {
            LOG.error("rpc call excepiton", e);
        }
    }

}