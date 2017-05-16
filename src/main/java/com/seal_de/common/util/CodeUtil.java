package com.seal_de.common.util;

/**
 * Created by seal_de on 2017/4/14.
 */
public class CodeUtil {
    public static String createToken() {
        String now = String.valueOf(System.currentTimeMillis());
        String token = EncryptUtil.encypt(now);
        return token;
    }
}
