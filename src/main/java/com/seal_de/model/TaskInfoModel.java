package com.seal_de.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by sealde on 4/29/17.
 */
public class TaskInfoModel {
    /** Paper **/
    private String paperName;
    private String subject;
    private String grade;
    /** Task **/
    private String taskId;
    private Date createTime;
    private Integer status;

    public TaskInfoModel(){}

    public TaskInfoModel(Task task) {
        Paper paper = task.getPaperId();

        this.setTaskId(task.getId());
        this.setStatus(task.getStatus());
        this.setCreateTime(task.getCreateTime());
        this.setGrade(paper.getGrade());
        this.setSubject(paper.getSubject());
        this.setPaperName(paper.getPaperName());
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
