package com.seal_de.Dao;

import com.seal_de.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sealde on 4/25/17.
 */
@Repository
public interface PaperRepository extends JpaRepository<Paper, String>, IRepository<Paper>{
}
