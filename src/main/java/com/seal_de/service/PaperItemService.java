package com.seal_de.service;

import com.seal_de.domain.PaperItem;

import java.util.List;

/**
 * Created by sealde on 5/5/17.
 */
public interface PaperItemService extends IService<PaperItem> {
    PaperItem getByPaperDetailIdAndChildIndex(String paperDetailId, Integer childIndex);
    List<PaperItem> findByPaperDetailId(String paperDetailId);
    List<PaperItem> reduceChildIndex(List<PaperItem> paperItems, int childIndex);
}
