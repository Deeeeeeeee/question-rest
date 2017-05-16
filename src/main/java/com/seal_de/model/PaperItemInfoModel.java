package com.seal_de.model;

import com.seal_de.domain.PaperItem;

/**
 * Created by sealde on 5/5/17.
 */
public class PaperItemInfoModel {
    private Integer parentIndex;
    private PaperItem paperItem;

    public Integer getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(Integer parentIndex) {
        this.parentIndex = parentIndex;
    }

    public PaperItem getPaperItem() {
        return paperItem;
    }

    public void setPaperItem(PaperItem paperItem) {
        this.paperItem = paperItem;
    }
}
