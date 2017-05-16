package com.seal_de.service;

import com.seal_de.Dao.ProvincesRepository;
import com.seal_de.domain.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvincesService {
    @Autowired
    private ProvincesRepository provincesRepository;

    public List<Provinces> findAll(){
        return provincesRepository.findAll();
    }
}
