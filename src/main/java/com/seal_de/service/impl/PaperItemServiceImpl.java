package com.seal_de.service.impl;

import com.seal_de.Dao.PaperItemRepository;
import com.seal_de.domain.PaperItem;
import com.seal_de.service.PaperItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by sealde on 5/5/17.
 */
@Service
@Transactional
public class PaperItemServiceImpl extends AbstractServiceImpl<PaperItemRepository, PaperItem>
        implements PaperItemService {
    @Autowired
    public PaperItemServiceImpl(PaperItemRepository paperItemRepository) {
        this.repository = paperItemRepository;
    }

    public PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, Integer childIndex) {
        return repository.getByPaperDetailIdAndChildIndex(paperDetailId, childIndex);
    }

    public List<PaperItem> findByPaperDetailId(String paperDetailId) {
        return repository.findByPaperDetailId(paperDetailId);
    }

    public List<PaperItem> reduceChildIndex(List<PaperItem> paperItems, int childIndex) {
        paperItems = modChildIndex(paperItems, childIndex, -1);
        save(paperItems, childIndex);
        return paperItems;
    }

    private List<PaperItem> modChildIndex(List<PaperItem> paperItems, int childIndex, int num) {
        PaperItem currentItem = null;
        ListIterator<PaperItem> it = paperItems.listIterator(childIndex);
        while(it.hasNext()) {
            currentItem = it.next();
            currentItem.setChildIndex(currentItem.getChildIndex() + num);
            it.set(currentItem);
        }
        return paperItems;
    }
}
