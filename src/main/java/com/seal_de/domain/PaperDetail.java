package com.seal_de.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sealde on 5/5/17.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "paper_detail", schema = "question", catalog = "")
public class PaperDetail {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String paperId;
    private String questionType;
    private Integer parentIndex;
    private List<PaperItem> paperItems;

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paper_id")
    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "question_type")
    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Basic
    @Column(name = "parent_index")
    public Integer getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(Integer parentIndex) {
        this.parentIndex = parentIndex;
    }

    @OneToMany(targetEntity = PaperItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "paper_detail_id")
    public List<PaperItem> getPaperItems() {
        return paperItems;
    }

    public void setPaperItems(List<PaperItem> paperItems) {
        this.paperItems = paperItems;
    }

    @Override
    public String toString() {
        return "PaperDetail{" +
                "id='" + id + '\'' +
                ", paperId='" + paperId + '\'' +
                ", questionType='" + questionType + '\'' +
                ", parentIndex=" + parentIndex +
                ", paperItems=" + paperItems +
                '}';
    }
}
