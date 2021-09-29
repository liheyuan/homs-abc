/**
 * @(#)NacosGrpcClientManager.java, 9月 29, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.client;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author coder4
 */
public class NacosGrpcClientManager<T extends MyGrpcClient> extends AbstractGrpcClientManager<T> {

    protected String serviceName;

    protected String nacosServer;

    protected NamingService namingService;

    public NacosGrpcClientManager(Class<T> kind, String nacosServer, String serviceName) {
        super(kind);
        this.nacosServer = nacosServer;
        this.serviceName = serviceName;
    }

    @Override
    public void init() throws Exception {
        namingService = NamingFactory
                .createNamingService(nacosServer);
        namingService.subscribe(serviceName, e -> {
            if (e instanceof NamingEvent) {
                NamingEvent event = (NamingEvent) e;
                rebuildClientPools(event.getInstances());
            }
        });
        rebuildClientPools(namingService.selectInstances(serviceName, true));
    }

    private void rebuildClientPools(List<Instance> instanceList) {
        ArrayList<MyGrpcClient> list = new ArrayList<>();
        for (Instance instance : instanceList) {
            MyGrpcClient client = null;
            try {
                client = kind.newInstance();
                client.setChannel(buildManagedChannel(instance.getIp(), instance.getPort()));
                client.init();
                list.add(client);
            } catch (Exception e) {
                LOG.error("build MyGrpcClient exception, instance = " + instance.toString(), e);
            }
        }
        CopyOnWriteArrayList<T> oldClientPools = clientPools;
        clientPools = new CopyOnWriteArrayList(list);
        // destory old ones
        oldClientPools.forEach(c -> {
            try {
                c.shutdownNow();
            } catch (InterruptedException e) {
                LOG.error("MyGrpcClient shutdown exception", e);
            }
        });
    }

//    public static void main(String[] args) throws Exception {
//        NacosGrpcClientManager<HomsDemoClient> manager =
//                new NacosGrpcClientManager<>(HomsDemoClient.class, "127.0.0.1:8848", "homs-demo");
//        manager.init();
//        manager.getClient().ifPresent(c -> System.out.println(c.add(1, 2)));
//        Thread.sleep(10000);
//        manager.shutdown();
//    }

}