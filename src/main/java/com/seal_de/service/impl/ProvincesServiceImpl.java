package com.seal_de.service.impl;

import com.seal_de.Dao.ProvincesRepository;
import com.seal_de.domain.Provinces;
import com.seal_de.service.ProvincesService;
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
public class ProvincesServiceImpl implements ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;

    @Cacheable(value = "provincesCache", key = "'provinces'")
    public List<Provinces> findAll(){
        return provincesRepository.findAll();
    }
}
