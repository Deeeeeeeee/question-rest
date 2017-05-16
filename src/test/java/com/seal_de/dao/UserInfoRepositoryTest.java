package com.seal_de.dao;

import com.seal_de.Dao.UserInfoRepository;
import com.seal_de.domain.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * Created by sealde on 5/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class UserInfoRepositoryTest {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Test
    @Transactional
    public void getByUsername() {
        UserInfo userInfo = userInfoRepository.getByUsername("jm1");
        Assert.assertEquals(userInfo.getRole(), new Integer(1));
    }

    @Test
    @Transactional
    public void saveOrUpdate() {
        UserInfo userInfo = userInfoRepository.getByUsername("jm1");
        userInfo.setRole(1);
        userInfoRepository.saveOrUpdate(userInfo);
    }

    @Test
    @Transactional
    public void save() {
        UserInfo userInfo = createUser();
        userInfoRepository.saveOrUpdate(userInfo);
    }

    @Test
    @Transactional
    public void getById() {
        UserInfo userInfo = userInfoRepository.getById("12");
        Assert.assertEquals(userInfo.getUsername(), "jm1");
    }

    @Test
    @Transactional
    public void delete() {
        UserInfo userInfo = userInfoRepository.getByUsername("jm3");
        userInfoRepository.delete(userInfo);
    }

    private UserInfo createUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("jm3");
        userInfo.setPassword("qqq");
        userInfo.setRole(2);
        return userInfo;
    }
}
