package com.seal_de.Dao;

import com.seal_de.domain.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvincesRepository extends JpaRepository<Provinces, Integer> {
}
