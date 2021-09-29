/**
 * @(#)SimpleGrpcClientManager.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author coder4
 */
public class SimpleGrpcClientManager<T extends MyGrpcClient> extends AbstractGrpcClientManager<T> {

    protected Logger LOG = LoggerFactory.getLogger(SimpleGrpcClientManager.class);

    private String ip;

    private int port;

    public SimpleGrpcClientManager(Class<T> kind, String ip, int port) {
        super(kind);
        this.ip = ip;
        this.port = port;
    }

    public void init() throws Exception {
        // init one client only
        MyGrpcClient client = kind.newInstance();
        client.setChannel(buildManagedChannel(ip, port));
        client.init();
        clientPools = new CopyOnWriteArrayList(Arrays.asList(client));
    }

    public static void main(String[] args) throws Exception {
        SimpleGrpcClientManager<HomsDemoGrpcClient> manager = new SimpleGrpcClientManager(HomsDemoGrpcClient.class, "127.0.0.1", 5000);
        manager.init();
        manager.getClient().ifPresent(t -> System.out.println(t.add(1, 2)));
        manager.shutdown();
    }

}