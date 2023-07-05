package com.jp.common.utils;

import org.springframework.stereotype.Component;

/**
 * 密码校验工具类
 * @author pibigstar
 *
 */
@Component
public class PasswordUtil {
    /**
     * <校验密码复杂度>
     *
     * @param password password
     * @return 结果
     * @throws
     */
    public Integer validatePasswdComplexity(String password)
    {
        int count = 0;
        if (password.length() >= 8)
        {
            count++;
        }
        if (password.length() - password.replaceAll("[A-Z]", "").length() > 0)
        {
            count++;
        }
        if (password.length() - password.replaceAll("[a-z]", "").length() > 0)
        {
            count++;
        }
        if (password.length() - password.replaceAll("[0-9]", "").length() > 0)
        {
            count++;
        }
        if (password.replaceAll("[0-9,A-Z,a-z]", "").length() > 0)
        {
            count++;
        }
        return count;
    }
}
