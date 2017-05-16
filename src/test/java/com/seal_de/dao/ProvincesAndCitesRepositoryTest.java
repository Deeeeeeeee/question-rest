package com.seal_de.dao;

import com.seal_de.Dao.CitiesRepository;
import com.seal_de.Dao.ProvincesRepository;
import com.seal_de.domain.Cities;
import com.seal_de.domain.Provinces;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sealde on 5/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class ProvincesAndCitesRepositoryTest {
    @Autowired
    private ProvincesRepository provincesRepository;
    @Autowired
    private CitiesRepository citiesRepository;

    @Test
    @Transactional
    public void findAll() {
        List<Provinces> list = provincesRepository.findAll();
        Assert.assertEquals(list.size(), 34);
    }

    @Test
    @Transactional
    public void findByProvinceId() {
        List<Cities> list = citiesRepository.findByProvinceId("130000");
        Assert.assertEquals(list.size(), 11);
    }
}
