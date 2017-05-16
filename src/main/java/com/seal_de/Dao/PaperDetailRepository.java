package com.seal_de.Dao;

import com.seal_de.domain.PaperDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
@Repository
public interface PaperDetailRepository extends JpaRepository<PaperDetail, String>, IRepository<PaperDetail> {
    @Query("select p from PaperDetail p where p.paperId = ? order by p.parentIndex asc")
    List<PaperDetail> findByPaperId(String paperId);
    PaperDetail getByPaperIdAndParentIndex(String paperId, Integer parentIndex);
}
