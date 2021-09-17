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
import com.coder4.homs.demo.HomsDemoProto.AddSingleRequest;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author coder4
 */
public class HomsDemoClient {

    private Logger LOG = LoggerFactory.getLogger(HomsDemoClient.class);

    private final HomsDemoGrpc.HomsDemoBlockingStub blockingStub;

    private final HomsDemoGrpc.HomsDemoStub stub;

    /**
     * Construct client for accessing HelloWorld server using the existing channel.
     */
    public HomsDemoClient(Channel channel) {
        blockingStub = HomsDemoGrpc.newBlockingStub(channel);
        stub = HomsDemoGrpc.newStub(channel);
    }

    public Optional<Integer> add(int val1, int val2) {
        AddRequest request = AddRequest.newBuilder().setVal1(val1).setVal2(val2).build();
        AddResponse response;
        try {
            response = blockingStub.add(request);
            return Optional.ofNullable(response.getVal());
        } catch (StatusRuntimeException e) {
            LOG.error("RPC failed: {0}", e.getStatus());
            return Optional.empty();
        }
    }

    public Optional<Integer> add2(Collection<Integer> vals) {

        try {

            CountDownLatch cdl = new CountDownLatch(1);

            AtomicLong respVal = new AtomicLong();

            StreamObserver<AddSingleRequest> requestStreamObserver =
                    stub.add2(new StreamObserver<AddResponse>() {
                        @Override
                        public void onNext(AddResponse value) {
                            respVal.set(value.getVal());
                        }

                        @Override
                        public void onError(Throwable t) {
                            cdl.countDown();
                        }

                        @Override
                        public void onCompleted() {
                            cdl.countDown();
                        }
                    });

            for (int val : vals) {
                requestStreamObserver.onNext(AddSingleRequest.newBuilder().setVal(val).build());
            }
            requestStreamObserver.onCompleted();

            try {
                cdl.await(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Optional.ofNullable(respVal.intValue());
        } catch (StatusRuntimeException e) {
            LOG.error("RPC failed: {0}", e.getStatus());
            return Optional.empty();
        }
    }

    // Test
    public static void main(String[] args) throws InterruptedException {

        Logger LOG = LoggerFactory.getLogger(HomsDemoClient.class);

        String target = "127.0.0.1:5000";
        ManagedChannel channel = null;
        try {
            channel = ManagedChannelBuilder
                    .forTarget(target)
                    .usePlaintext()
                    .build();
        } catch (Exception e) {
            LOG.error("open channel excepiton", e);
            return;
        }

        try {
            HomsDemoClient client = new HomsDemoClient(channel);
            System.out.println(client.add(1, 2));
            System.out.println(client.add2(Arrays.asList(1, 2, 3)));
        } catch (Exception e) {
            LOG.error("rpc call excepiton", e);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }


    }

}