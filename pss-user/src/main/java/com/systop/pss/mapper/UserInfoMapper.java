package com.systop.pss.mapper;

import com.systop.pss.model.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String uuId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String uuId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    /**
     * 查询所有用户信息
     * @return
     */
    List<UserInfo> selectUserList();

    /**
     * 根据用户手机号查询用户
     * @param selectMap
     * @return
     */
    UserInfo selectUserByTelPhoneAndPwd(Map<String, Object> selectMap);
}