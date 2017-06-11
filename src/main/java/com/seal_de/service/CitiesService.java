package com.seal_de.service;

import com.seal_de.domain.Cities;

import java.util.List;

public interface CitiesService {
    List<Cities> findByProvinceId(String id);
}
