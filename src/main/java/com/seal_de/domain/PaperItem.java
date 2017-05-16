package com.seal_de.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by sealde on 5/5/17.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "paper_item", schema = "question", catalog = "")
public class PaperItem {
    @JsonIgnore
    private String id;
    @JsonIgnore
    private String paperDetailId;
    private String stem;
    private String examPoint;
    private String answer;
    private String solution;
    private Integer childIndex;

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
    @Column(name = "paper_detail_id")
    public String getPaperDetailId() {
        return paperDetailId;
    }

    public void setPaperDetailId(String paperDetailId) {
        this.paperDetailId = paperDetailId;
    }

    @Basic
    @Column(name = "stem")
    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    @Basic
    @Column(name = "exam_point")
    public String getExamPoint() {
        return examPoint;
    }

    public void setExamPoint(String examPoint) {
        this.examPoint = examPoint;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "solution")
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Basic
    @Column(name = "child_index")
    public Integer getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(Integer childIndex) {
        this.childIndex = childIndex;
    }

    @Override
    public String toString() {
        return "PaperItem{" +
                "id='" + id + '\'' +
                ", paperDetailId='" + paperDetailId + '\'' +
                ", stem='" + stem + '\'' +
                ", examPoint='" + examPoint + '\'' +
                ", answer='" + answer + '\'' +
                ", solution='" + solution + '\'' +
                ", childIndex=" + childIndex +
                '}';
    }
}
