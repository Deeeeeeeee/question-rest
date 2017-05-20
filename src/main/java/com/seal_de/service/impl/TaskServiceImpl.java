package com.seal_de.service.impl;

import com.seal_de.Dao.TaskRepository;
import com.seal_de.domain.Paper;
import com.seal_de.domain.Task;
import com.seal_de.model.TaskInfoModel;
import com.seal_de.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sealde on 4/24/17.
 */
@Service
public class TaskServiceImpl extends AbstractServiceImpl<TaskRepository, Task> implements TaskService {
    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.repository = taskRepository;
    }

    @Override
    @Transactional
    public boolean save(Task task) {
        String id = task.getId();
        if(id == null || "".equals(id)) {
            task.setId(null);
            task.setCreateTime(new Date());
            task.setStatus(0);
        }
        repository.saveOrUpdate(task);
        return true;
    }

    @Transactional
    public List<Task> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Task getByStatus(Integer status) {
        return repository.getByStatus(status);
    }

    @Override
    @Transactional
    public List<Task> findByStatus(Integer status) {
        return repository.findByStatus(status);
    }

    public List<TaskInfoModel> taskToTaskInfoModel(List<Task> tasks) {
        List<TaskInfoModel> taskInfoModels = new ArrayList<>();
        for(Task task : tasks) {
            TaskInfoModel taskInfoModel = new TaskInfoModel();
            Paper paper = task.getPaperId();

            taskInfoModel.setTaskId(task.getId());
            taskInfoModel.setStatus(task.getStatus());
            taskInfoModel.setCreateTime(task.getCreateTime());
            taskInfoModel.setGrade(paper.getGrade());
            taskInfoModel.setSubject(paper.getSubject());
            taskInfoModel.setPaperName(paper.getPaperName());
            taskInfoModels.add(taskInfoModel);
        }
        return taskInfoModels;
    }

    @Override
    @Transactional
    public Task getByAuditorId(String auditorId) {
        return repository.getByAuditorIdAndStatus(auditorId, 21);
    }

    @Override
    public List<Task> findByAuditorId(String auditorId) {
        return repository.findByAuditorId(auditorId);
    }

    @Override
    public Task getNotMakingTask(String userId) {
        return repository.getByUserIdAndStatus(userId, 0);
    }
}
