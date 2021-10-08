/**
 * @(#)HomsDemoConfig.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.config;

import com.coder4.homs.demo.server.annotation.HSConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author coder4
 */
@Service
public class HomsDemoConfig {

    @HSConfig
    private int num;

    @HSConfig(name = "mapConfig")
    private Map<String, String> map;

    @PostConstruct
    public void postConstruct() {
        System.out.println(num);
        System.out.println(map);
    }

}