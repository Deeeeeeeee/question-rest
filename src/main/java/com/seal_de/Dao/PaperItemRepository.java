package com.seal_de.Dao;

import com.seal_de.domain.PaperItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sealde on 5/5/17.
 */
@Repository
public interface PaperItemRepository extends JpaRepository<PaperItem, String>, IRepository<PaperItem> {
    PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, Integer childIndex);

    @Query("select p from PaperItem p where p.paperDetailId = ? order by p.childIndex asc")
    List<PaperItem> findByPaperDetailId(String paperDetailId);
}
