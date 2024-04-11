package com.example.demo.model;

import java.util.concurrent.TimeUnit;

public class CacheEntry {
    private String value;
    private long expiryTimeMillis;
    private boolean isExpired = false;

    public CacheEntry(String value, long expiryTime, TimeUnit timeUnit) {
        this.value = value;
        this.expiryTimeMillis = System.currentTimeMillis() + timeUnit.toMillis(expiryTime);
    }

    public String getValue() {
        return value;
    }

    public boolean isExpired() {
        if (isExpired)
            return isExpired;
        this.isExpired = System.currentTimeMillis() >= expiryTimeMillis;
        return isExpired;
    }
}
