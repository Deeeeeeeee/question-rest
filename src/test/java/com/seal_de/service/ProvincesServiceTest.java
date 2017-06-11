package com.seal_de.service;

import com.seal_de.domain.Provinces;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sealde on 6/9/17.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProvincesServiceTest {
    @Autowired
    private ProvincesService provincesService;

    @Autowired
    private StringRedisTemplate stringRedis;

    @After
    public void cleanUp() {
        stringRedis.delete("provinces");
        stringRedis.delete("provincesCache~keys");
    }

    @Test
    public void findAll() {
        List<Provinces> list = provincesService.findAll();
        Assert.assertEquals(list.size(), 34);
    }
}
