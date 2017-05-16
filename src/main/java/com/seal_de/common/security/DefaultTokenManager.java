package com.seal_de.common.security;

import com.seal_de.common.util.CodeUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by seal_de on 2017/4/14.
 */
@Component
public class DefaultTokenManager implements TokenManager {
    private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

    public String createToken(String username) {
        String token = CodeUtil.createToken();
        tokenMap.put(token, username);
        return token;
    }

    public boolean checkToken(String token) {
        if(token == null || "".equals(token) || !tokenMap.containsKey(token))
           return false;
        return true;
    }

    public void removeToken(String token) {
        if(tokenMap.containsKey(token))
            tokenMap.remove(token);
    }

    public String getUsername(String token) {
        return tokenMap.get(token);
    }
}
