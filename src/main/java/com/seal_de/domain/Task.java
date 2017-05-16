package com.seal_de.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sealde on 4/25/17.
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "task")
public class Task {
    private String id;
    private Date createTime;
    private Integer status;
    private String userId;
    private Paper paperId;
    private String errorMessage;
    private String auditorId;
    private Date checkTime;

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
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @JsonIgnore
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @OneToOne
    @JoinColumn(name = "paper_id")
    public Paper getPaperId() {
        return paperId;
    }

    public void setPaperId(Paper paperId) {
        this.paperId = paperId;
    }

    @Basic
    @Column(name = "error_message")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Basic
    @Column(name = "auditor_id")
    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    @Basic
    @Column(name = "check_time")
    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", paperId=" + paperId +
                ", errorMessage='" + errorMessage + '\'' +
                ", auditorId='" + auditorId + '\'' +
                ", checkTime=" + checkTime +
                '}';
    }
}
