package com.seal_de.service.impl;

import com.seal_de.Dao.PaperDetailRepository;
import static com.seal_de.common.util.VerifyUtil.*;
import com.seal_de.domain.PaperDetail;
import com.seal_de.domain.PaperItem;
import com.seal_de.service.PaperDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sealde on 5/3/17.
 */
@Service
@Transactional
public class PaperDetailServiceImpl extends AbstractServiceImpl<PaperDetailRepository, PaperDetail> implements PaperDetailService {
    @Autowired
    public PaperDetailServiceImpl(PaperDetailRepository paperDetailRepository) {
        this.repository = paperDetailRepository;
    }

    public List<PaperDetail> findByPaperId(String paperId) {
        return repository.findByPaperId(paperId);
    }

    public List<PaperDetail> findByPaperIdAfterClear(String paperId) {
        repository.clear();
        return repository.findByPaperId(paperId);
    }

    public PaperDetail getByPaperIdAndParentIndex(String paperId, Integer parentIndex) {
        return repository.getByPaperIdAndParentIndex(paperId, parentIndex);
    }

    public List<PaperDetail> reduceParentIndex(List<PaperDetail> paperDetails, Integer parentIndex) {
        paperDetails = modParentIndex(paperDetails, parentIndex, -1);
        save(paperDetails, parentIndex);
        return paperDetails;
    }

    private List<PaperDetail> modParentIndex(List<PaperDetail> paperDetails, int parentIndex, int num) {
        PaperDetail currentDetail = null;
        ListIterator<PaperDetail> it = paperDetails.listIterator(parentIndex);
        while (it.hasNext()) {
            currentDetail = it.next();
            currentDetail.setParentIndex(currentDetail.getParentIndex() + num);
            it.set(currentDetail);
        }
        return paperDetails;
    }

    public void verifyDeletePaperDetail(PaperDetail paperDetail) {
        notNull(paperDetail, HttpStatus.NOT_FOUND, "删除出错：没有这道大题");
        List<PaperItem> paperItems = paperDetail.getPaperItems();
        isTrue(paperItems.size() == 0, HttpStatus.FORBIDDEN, "请删除完小题再删除大题");
    }
}
