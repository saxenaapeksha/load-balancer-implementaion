package org.example;

import java.util.ArrayList;
import java.util.List;

public class LoadBalancer {
    private List<String> ipAddresses;
    private final int limit = 10;

    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    public int getLimit() {
        return limit;
    }

    public LoadBalancer() {
        this.ipAddresses = new ArrayList<>(limit);
    }

    public void register(String ipAddress) throws MaxLimitExceeded {
        if (ipAddresses.size()  == limit)
            throw new MaxLimitExceeded("maxLimit of loadBalancer reached");
        if (ipAddresses.contains(ipAddress))
            return;
        ipAddresses.add(ipAddress);
    }

    public String get() {
        int rand = (int) Math.abs(Math.random() * 10);
        while (rand >= ipAddresses.size()) {
            rand = (int) Math.abs(Math.random() * 10);
        }
        return ipAddresses.get(rand);
    }
}