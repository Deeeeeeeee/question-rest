package com.seal_de.service;

import com.seal_de.Dao.CitiesRepository;
import com.seal_de.domain.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService {
    @Autowired
    private CitiesRepository citiesRepository;

    public List<Cities> findByProvinceId(String id){
        return citiesRepository.findByProvinceId(id);
    }
}
