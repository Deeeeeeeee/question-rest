package com.seal_de.service;

import com.seal_de.domain.PaperDetail;

import java.util.List;

/**
 * Created by sealde on 5/3/17.
 */
public interface PaperDetailService extends IService<PaperDetail> {
    boolean save(List<PaperDetail> paperDetails, int index);
    List<PaperDetail> findByPaperId(String paperId);
    List<PaperDetail> findByPaperIdAfterClear(String paperId);
    PaperDetail getByPaperIdAndParentIndex(String paperId, Integer parentIndex);
    List<PaperDetail> reduceParentIndex(List<PaperDetail> paperDetails, Integer parentIndex);
    void verifyDeletePaperDetail(PaperDetail paperDetail);
}
