/**
 * @(#)GrpcClient.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import io.grpc.ManagedChannel;

/**
 * @author coder4
 */
public interface MyGrpcClient {

    void setChannel(ManagedChannel channel);

    void init();

    void shutdownNow() throws InterruptedException;

}