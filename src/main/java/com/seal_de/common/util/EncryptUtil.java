package com.seal_de.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by seal_de on 2017/4/13.
 */
public class EncryptUtil {
    public static String encypt(String pwd) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.encode(pwd);
    }

    public static boolean matchs(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        return b.matches(rawPassword, encodedPassword);
    }
}
