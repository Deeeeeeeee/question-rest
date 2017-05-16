package com.seal_de.service;

import com.seal_de.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sealde on 5/4/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class TestUserInfoService {
//    @Autowired
//    private UserInfoService userInfoService;

    @Test
    public void testSave() {
//        userInfoService.save(createUserInfo1());
    }

    @Test
    public void testDelete() {
//        UserInfo userInfo = userInfoService.getById("ff8081815bd2ba25015bd2ba2e7f0000");
//        userInfoService.delete(userInfo);
    }

    private UserInfo createUserInfo1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("测试");
        userInfo.setPassword("1234");
        return userInfo;
    }
}
