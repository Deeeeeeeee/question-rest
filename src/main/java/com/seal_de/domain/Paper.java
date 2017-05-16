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
@Table(name = "paper")
public class Paper {
    @JsonIgnore
    private String id;
    private String paperType;
    private String year;
    private String subject;
    private String grade;
    private String school;
    private String paperName;
    private String region;
    private String oldUrl;
    private String url;

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
    @Column(name = "paper_type")
    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "paper_name")
    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "old_url")
    public String getOldUrl() {
        return oldUrl;
    }

    public void setOldUrl(String oldUrl) {
        this.oldUrl = oldUrl;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (id != null ? !id.equals(paper.id) : paper.id != null) return false;
        if (paperType != null ? !paperType.equals(paper.paperType) : paper.paperType != null) return false;
        if (year != null ? !year.equals(paper.year) : paper.year != null) return false;
        if (subject != null ? !subject.equals(paper.subject) : paper.subject != null) return false;
        if (grade != null ? !grade.equals(paper.grade) : paper.grade != null) return false;
        if (school != null ? !school.equals(paper.school) : paper.school != null) return false;
        if (paperName != null ? !paperName.equals(paper.paperName) : paper.paperName != null) return false;
        if (region != null ? !region.equals(paper.region) : paper.region != null) return false;
        if (oldUrl != null ? !oldUrl.equals(paper.oldUrl) : paper.oldUrl != null) return false;
        if (url != null ? !url.equals(paper.url) : paper.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (paperType != null ? paperType.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (paperName != null ? paperName.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (oldUrl != null ? oldUrl.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
