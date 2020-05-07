package com.myshop.prototype.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.DigestUtils;

public class md5 {
    private static final String slat = "&%5123***&&%%$$#@";

    @NotNull
    public static String originMD5(String str){
        String base = str + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
