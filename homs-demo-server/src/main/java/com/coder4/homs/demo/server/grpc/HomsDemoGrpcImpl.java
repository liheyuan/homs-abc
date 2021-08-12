/**
 * @(#)HomsDemoImpl.java, 8月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.grpc;

import com.coder4.homs.demo.HomsDemoGrpc.HomsDemoImplBase;
import com.coder4.homs.demo.HomsDemoProto.AddRequest;
import com.coder4.homs.demo.HomsDemoProto.AddResponse;
import com.coder4.homs.demo.HomsDemoProto.AddSingleRequest;
import io.grpc.stub.StreamObserver;

/**
 * @author coder4
 */
public final class HomsDemoGrpcImpl extends HomsDemoImplBase {

    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        responseObserver.onNext(AddResponse.newBuilder()
                .setVal(request.getVal1() + request.getVal2())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<AddSingleRequest> add2(StreamObserver<AddResponse> responseObserver) {
        return new StreamObserver<AddSingleRequest>() {

            int sum = 0;

            @Override
            public void onNext(AddSingleRequest value) {
                sum += value.getVal();
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(AddResponse.newBuilder()
                        .setVal(sum)
                        .build());
                sum = 0;
                responseObserver.onCompleted();
            }
        };
    }
}