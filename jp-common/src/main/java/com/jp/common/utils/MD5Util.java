package com.jp.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @author pibigstar
 *
 */
@Component
@Slf4j
public class MD5Util {
    //盐，用于混交md5
    private static final String slat = "&%5123***&&%%$$#@";


    public String getPhpMD5(String txt) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes("GBK"));    //问题主要出在这里，Java的字符串是unicode编码，不受源码文件的编码影响；而PHP的编码是和源码文件的编码一致，受源码编码影响。
            StringBuilder buf = new StringBuilder();
            for(byte b:md.digest()){
                buf.append(String.format("%02x", b&0xff));
            }
            return  buf.toString();
        }catch( Exception e ){
            e.printStackTrace();
            return null;
        }
    }
}
