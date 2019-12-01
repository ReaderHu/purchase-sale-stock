package com.systop.pss.service;

import com.systop.pss.model.UserPassword;

/**
 * @Description
 * @auther wang
 * @create 2019-12-01
 */
public interface UserPasswordService {
    /**
     * g根据用户id更新用户密码
     * @param userPassword
     * @return
     */
    int updateUserPwdByuuId(UserPassword userPassword);
}
