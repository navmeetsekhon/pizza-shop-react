package com.orderManagement.orderManagementApp.utils;

import java.util.UUID;

public class IdGeneratorUtil {
    public static String generateId(String prefix) {
        return prefix.concat(generateId());
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
