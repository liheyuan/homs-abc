/**
 * @(#)DemoMessage.java, 10月 12, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server.mq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author coder4
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoMessage {

    private String msg;

    private long ts;
}