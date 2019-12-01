package com.systop.pss.service.impl;

import com.systop.pss.mapper.UserPasswordMapper;
import com.systop.pss.model.UserPassword;
import com.systop.pss.service.UserPasswordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @auther wang
 * @create 2019-12-01
 */
@Service("userPasswordServiceImpl")
public class UserPasswordServiceImpl implements UserPasswordService {
    @Resource
    private UserPasswordMapper userPasswordMapper;

    /**
     * g根据用户id更新用户密码
     * @param userPassword
     * @return
     */
    @Override
    public int updateUserPwdByuuId(UserPassword userPassword) {
        return userPasswordMapper.updatePwdByuuId(userPassword);
    }
}
