package com.seal_de.dao;

import com.seal_de.Dao.TaskRepository;
import com.seal_de.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by sealde on 5/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    @Transactional
    public void findByUserId() {
        List<Task> tasks = taskRepository.findByUserId("12");
        System.out.println(tasks);
    }

    @Test
    @Transactional
    public void findByStatus() {
        List<Task> tasks = taskRepository.findByStatus(30);
        System.out.println(tasks);
    }

    @Test
    @Transactional
    public void getByStatus() {
        Task task = taskRepository.getByStatus(30);
        System.out.println(task);
    }

    @Test
    @Transactional
    public void getByAuditorIdAndStatus() {
        Task task = taskRepository.getByAuditorIdAndStatus("ff8081815c06b949015c06ba03580000", 30);
        System.out.println(task);
    }

    @Test
    @Transactional
    public void findByAuditorId() {
        List<Task> tasks = taskRepository.findByAuditorId("ff8081815c06b949015c06ba03580000");
        System.out.println(tasks);
    }

    @Test
    @Transactional
    public void getByUserIdAndStatus() {
        Task task = taskRepository.getByUserIdAndStatus("12", Integer.valueOf(0));
        System.out.println(task);
    }
}
