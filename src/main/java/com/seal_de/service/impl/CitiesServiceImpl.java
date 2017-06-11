package com.seal_de.service.impl;

import com.seal_de.Dao.CitiesRepository;
import com.seal_de.domain.Cities;
import com.seal_de.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sealde on 6/11/17.
 */
@Service
@Transactional
public class CitiesServiceImpl implements CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    @Cacheable(value = "citiesCache", key = "#provinceId")
    public List<Cities> findByProvinceId(String provinceId){
        return citiesRepository.findByProvinceId(provinceId);
    }
}
