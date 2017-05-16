package com.seal_de.service.impl;

import com.seal_de.Dao.UserInfoRepository;
import static com.seal_de.common.util.VerifyUtil.*;

import com.seal_de.common.util.EncryptUtil;
import com.seal_de.domain.UserInfo;
import com.seal_de.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {

    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public boolean saveUserInfo(UserInfo userInfo){
        UserInfo oldUser = getByUsername(userInfo.getUsername());
        isNull(oldUser, HttpStatus.CONFLICT, "用户名不能重复");

        String pwd = userInfo.getPassword();
        userInfo.setPassword(EncryptUtil.encypt(pwd));

        if(userInfo.getId() !=null)
            userInfo.setId(null);
        userInfoRepository.save(userInfo);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserInfo getByUsername(String username) {
        return userInfoRepository.getByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = getByUsername(username);
        if(userInfo == null)
            throw new UsernameNotFoundException("账号不存在");
        return new User(userInfo.getUsername(), userInfo.getPassword(), Collections.<GrantedAuthority>emptyList());
    }
}
