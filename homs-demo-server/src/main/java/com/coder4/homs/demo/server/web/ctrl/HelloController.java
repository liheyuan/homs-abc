/**
 * @(#)HelloController.java, 8月 13, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.web.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coder4
 */
@RestController
public class HelloController {

    @GetMapping("/sleep")
    public void sleep(@RequestParam int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}