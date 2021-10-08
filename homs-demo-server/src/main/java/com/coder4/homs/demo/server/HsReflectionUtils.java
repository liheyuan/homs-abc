/**
 * @(#)HsReflectionUtils.java, 10月 08, 2021.
 * <p>
 * Copyright 2021 coder4.com. All rights reserved.
 * CODER4.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.coder4.homs.demo.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;

/**
 * @author coder4
 */
public class HsReflectionUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void setField(Object bean, Field field, String valueStr) throws IllegalAccessException {
        field.setAccessible(true);
        Class fieldType = field.getType();
        if (fieldType == Integer.TYPE || fieldType == Integer.class) {
            field.set(bean, Integer.parseInt(valueStr));
        } else if (fieldType == Long.TYPE || fieldType == Long.class) {
            field.set(bean, Long.parseLong(valueStr));
        } else if (fieldType == Short.TYPE || fieldType == Short.class) {
            field.set(bean, Short.parseShort(valueStr));
        } else if (fieldType == Double.TYPE || fieldType == Double.class) {
            field.set(bean, Double.parseDouble(valueStr));
        } else if (fieldType == Float.TYPE || fieldType == Float.class) {
            field.set(bean, Float.parseFloat(valueStr));
        } else if (fieldType == Byte.TYPE || fieldType == Byte.class) {
            field.set(bean, Byte.parseByte(valueStr));
        } else if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
            field.set(bean, Boolean.parseBoolean(valueStr));
        } else if (fieldType == Character.TYPE || fieldType == Character.class) {
            if (valueStr == null || valueStr.isEmpty()) {
                throw new IllegalArgumentException("can't parse char because value string is empty");
            }
            field.set(bean, valueStr.charAt(0));
        } else if (fieldType.isEnum()) {
            field.set(bean, Enum.valueOf(fieldType, valueStr));
        } else {
            try {
                field.set(bean, OBJECT_MAPPER.readValue(valueStr, fieldType));
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("can't parse json because exception");
            }
        }
    }

}