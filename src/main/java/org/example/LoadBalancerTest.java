package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoadBalancerTest {
    LoadBalancer loadBalancer;

    @Before
    public void setup() {
        loadBalancer = new LoadBalancer();
    }

    @Test
    public void testRegister() throws MaxLimitExceeded {
        String ipAddress = "121.221.111";
        loadBalancer.register(ipAddress);
        Assert.assertEquals(ipAddress,loadBalancer.getIpAddresses().get(0));
    }

    @Test
    public void testRegisterNoDuplicate() throws MaxLimitExceeded {
        String ipAddress = "121.221.111";
        loadBalancer.register(ipAddress);
        loadBalancer.register(ipAddress);
        Assert.assertEquals(1,loadBalancer.getIpAddresses().size());
    }

    @Test
    public void testRegisterLimitExceeded() throws MaxLimitExceeded {
        String ipAddress = "121.221.111";
        for (int i = 0; i < 10; i++) {
            loadBalancer.register(ipAddress+i);
        }
        String errorMessage = "maxLimit of loadBalancer reached";
        Throwable throwable = Assert.assertThrows(MaxLimitExceeded.class, () -> { loadBalancer.register(ipAddress);});
        Assert.assertEquals(errorMessage,throwable.getMessage());
    }

    @Test
    public void testGet() throws MaxLimitExceeded {
        testRegisterLimitExceeded();
        String ipAddress = loadBalancer.get();
        Assert.assertNotNull(ipAddress);
    }
}
