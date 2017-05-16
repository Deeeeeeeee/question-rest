package com.seal_de.Dao;

import com.seal_de.domain.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {
    List<Cities> findByProvinceId(String provinceId);
}
