package com.seal_de.service;

import com.seal_de.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserInfoService extends UserDetailsService{
    boolean saveUserInfo(UserInfo userInfo);
    UserInfo getByUsername(String username);
}
