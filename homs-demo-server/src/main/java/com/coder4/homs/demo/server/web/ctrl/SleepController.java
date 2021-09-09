/**
 * @(#)SleepController.java, 9月 09, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coder4
 */
@RestController
@RequestMapping(value = "/sleep")
public class SleepController {

    private Logger LOG = LoggerFactory.getLogger(SleepController.class);

    @GetMapping
    public void sleep(@RequestParam(defaultValue = "3") int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            LOG.error("exception", e);
        }
    }

}