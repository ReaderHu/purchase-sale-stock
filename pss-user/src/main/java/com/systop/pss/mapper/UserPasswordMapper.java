package com.systop.pss.mapper;

import com.systop.pss.model.UserPassword;

public interface UserPasswordMapper {
    int deleteByPrimaryKey(Integer pwdId);

    int insert(UserPassword record);

    int insertSelective(UserPassword record);

    UserPassword selectByPrimaryKey(Integer pwdId);

    int updateByPrimaryKeySelective(UserPassword record);

    int updateByPrimaryKey(UserPassword record);

    String selectPwdByUuId(String uuId);

    /**
     * g根据用户id更新用户密码
     * @param userPassword
     * @return
     */
    int updatePwdByuuId(UserPassword userPassword);
}