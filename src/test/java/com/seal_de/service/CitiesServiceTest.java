package com.seal_de.service;

import com.seal_de.domain.Cities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sealde on 6/11/17.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CitiesServiceTest {
    @Autowired
    private CitiesService citiesService;

    @Autowired
    private StringRedisTemplate stringRedis;

    @After
    public void cleanUp() {
        stringRedis.delete("citiesCache~keys");
        stringRedis.delete("440000");
    }

    @Test
    public void findByProvinceId() {
        List<Cities> list = citiesService.findByProvinceId("440000");
        Assert.assertEquals(list.size(), 21);
    }
}
