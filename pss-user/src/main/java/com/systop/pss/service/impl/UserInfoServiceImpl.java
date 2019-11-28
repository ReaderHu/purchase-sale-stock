package com.systop.pss.service.impl;

import com.systop.pss.mapper.UserInfoMapper;
import com.systop.pss.model.UserInfo;
import com.systop.pss.service.UserInfoServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userInfoServiceImpl")
public class UserInfoServiceImpl implements UserInfoServcie {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(String uuId) {
        return userInfoMapper.deleteByPrimaryKey(uuId);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        return userInfoMapper.insertSelective(record);
    }

    @Override
    public UserInfo selectByPrimaryKey(String uuId) {
        return userInfoMapper.selectByPrimaryKey(uuId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

}
